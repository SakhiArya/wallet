package com.agro.wallet.service.impl;

import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.repository.TransactionRepository;
import com.agro.wallet.service.TransactionEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
