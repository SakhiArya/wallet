package com.agro.wallet.repository;

import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.response.Transactions;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseRepository<TransactionEntity, Integer> {

    @Query(nativeQuery = true)
    List<Transactions> getAllTransactionForWalletId(
        @Param("walletId") String walletId);

    TransactionEntity findByTxnId(String txnId);

}
