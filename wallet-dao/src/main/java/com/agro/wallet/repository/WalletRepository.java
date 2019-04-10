package com.agro.wallet.repository;

import com.agro.wallet.entities.WalletEntity;

public interface WalletRepository extends BaseRepository<WalletEntity, Integer> {

    public WalletEntity findbyUserId(String userId);

}
