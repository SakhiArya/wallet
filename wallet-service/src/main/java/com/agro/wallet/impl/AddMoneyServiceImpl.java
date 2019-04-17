package com.agro.wallet.impl;


import com.agro.wallet.AddMoneyService;
import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.response.AddMoneyOutput;
import com.agro.wallet.service.TransactionEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.CommonUtils;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddMoneyServiceImpl implements AddMoneyService {

    @Autowired
    WalletEntityService walletEntityService;

    @Autowired
    LoginStore loginStore;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    TransactionEntityService transactionEntityService;

    @Override
    public AddMoneyOutput addMoney(AddMoneyInput addMoneyInput) {
        log.info("start addMoney");
        LoginData loginData = loginStore.getValue(addMoneyInput.getToken());
        WalletEntity walletEntity = walletEntityService.findById(loginData.getWalletId());
        Double currentBalance = walletEntity.getBalance();
        Double updatedBalance = currentBalance + addMoneyInput.getAmount();
        walletEntity.setBalance(updatedBalance);
        walletEntityService.getDao().save(walletEntity);

        TransactionEntity transactionEntity = TransactionEntity.builder()
            .amount(addMoneyInput.getAmount())
            .note("self")
            .payeeWalletId(walletEntity)
            .payerWalletId(walletEntity)
            .status(TransactionStatus.SUCCESS)
            .txnId(commonUtils.generateUUID("TXN"))
            .build();
        transactionEntity = transactionEntityService.getDao().save(transactionEntity);

        log.info("end addMoney");
        return AddMoneyOutput.builder().txnId(transactionEntity.getTxnId())
            .newBalance("Your updated "
                + "balance is "
                + "" + updatedBalance)
            .build();
    }
}
