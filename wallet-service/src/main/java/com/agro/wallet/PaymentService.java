package com.agro.wallet;

import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.PaymentOutput;

public interface PaymentService {

    PaymentOutput payment(PaymentInput paymentInput, WalletEntity payeeWallet,
        WalletEntity payerWallet, String txnId);

    TransactionEntity createTransaction(PaymentInput paymentInput, WalletEntity payeeWallet,
        WalletEntity payerWallet);

    Boolean updateTransactionStatus(String txnId, TransactionStatus transactionStatus);

}
