package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.CancelTransactionService;
import com.agro.wallet.WalletException;
import com.agro.wallet.request.CancelTransactionInput;
import com.agro.wallet.request.CancelTransactionOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cancelTransactionApi")
public class CancelTransactionApi extends ApiService<CancelTransactionInput, CancelTransactionOutput> {

    @Autowired
    private CancelTransactionService cancelTransactionService;

    @Override
    public CancelTransactionOutput callApi(CancelTransactionInput input) throws WalletException {
        return cancelTransactionService.cancelTransaction(input);
    }
}
