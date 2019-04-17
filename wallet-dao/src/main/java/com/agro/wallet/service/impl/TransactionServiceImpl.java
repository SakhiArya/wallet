package com.agro.wallet.service.impl;

import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.repository.TransactionRepository;
import com.agro.wallet.response.ReceivedTransactions;
import com.agro.wallet.response.Transactions;
import com.agro.wallet.service.TransactionEntityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends
    BaseServiceImpl<TransactionEntity, Integer> implements
    TransactionEntityService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionRepository getDao() {
        return transactionRepository;
    }

    @Override
    public List<Transactions> getAllTransactionsForWalletId(String walletId) {
        return transactionRepository.getAllTransactionForWalletId(walletId);
    }

    @Override
    public List<ReceivedTransactions> getAllReceivedTransactionForWalletId(String walletId) {
        return transactionRepository.getAllReceivedTransactionForWalletId(walletId);
    }

    @Override
    public TransactionEntity findByTxnId(String txnId) {
        return transactionRepository.findByTxnId(txnId);
    }
}
