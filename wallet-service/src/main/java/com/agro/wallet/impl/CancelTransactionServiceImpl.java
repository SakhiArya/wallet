package com.agro.wallet.impl;

import com.agro.wallet.CancelTransactionService;
import com.agro.wallet.FetchTxnService;
import com.agro.wallet.PaymentService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.request.CancelTransactionInput;
import com.agro.wallet.request.CancelTransactionOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CancelTransactionServiceImpl implements CancelTransactionService {

    @Autowired
    PaymentService paymentService;

    @Autowired
    FetchTxnService fetchTxnService;

    @Override
    public CancelTransactionOutput cancelTransaction(
        CancelTransactionInput cancelTransactionInput) {
        TransactionEntity transaction;
        CancelTransactionOutput cancelTransactionOutput =CancelTransactionOutput.builder()
            .txnId(cancelTransactionInput.getTxnId())
            //.origTransactionStatus(transaction.getStatus())
            .build();
        try {
            paymentService.updateTransactionStatus(cancelTransactionInput.getTxnId(),
                TransactionStatus.CANCELED);
        }catch (WalletException we){
            log.info("Unable to cancel Transaction, txn already in terminal state");
        }
        transaction = fetchTxnService.fetchOne(cancelTransactionInput.getTxnId());
        cancelTransactionOutput.setFinalTransactionStatus(transaction.getStatus());
        return cancelTransactionOutput;
    }
}
