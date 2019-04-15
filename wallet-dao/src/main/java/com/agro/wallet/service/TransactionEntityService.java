package com.agro.wallet.service;

import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.response.Transactions;
import java.util.List;

public interface TransactionEntityService extends BaseService<TransactionEntity,Integer> {

    List<Transactions> getAllTransactionsForWalletId(String walletId);
    TransactionEntity findByTxnId(String txnId);
}
