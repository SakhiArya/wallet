package com.agro.wallet.repository;

import com.agro.wallet.entities.TransactionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseRepository<TransactionEntity, Integer> {

}
