package com.agro.wallet.repository;

import com.agro.wallet.entities.WalletEntity;

public interface WalletRepository extends BaseRepository<WalletEntity, Integer> {


    WalletEntity findByWalletId(String walletId);

    void deleteByWalletId(String walletId);
}
