package com.agro.wallet.impl;


import com.agro.wallet.FetchTxnService;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.request.FetchTxnInput;
import com.agro.wallet.response.FetchTxnOutput;
import com.agro.wallet.response.Transactions;
import com.agro.wallet.service.TransactionEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchTxnServiceImpl implements FetchTxnService {

    @Autowired
    TransactionEntityService transactionEntityService;

    @Autowired
    LoginStore loginStore;

    @Override
    public FetchTxnOutput fetchTxn(FetchTxnInput fetchTxnInput) {
        LoginData loginData=loginStore.getValue(fetchTxnInput.getToken());

        List<Transactions> transactions=transactionEntityService.getAllTransactionsForWalletId(loginData
            .getWalletId());

        return FetchTxnOutput.builder().transactions(transactions).build();
    }

    @Override
    public TransactionEntity fetchOne(String txnId){
        return transactionEntityService.findByTxnId(txnId);
    }
}
