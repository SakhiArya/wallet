package com.agro.wallet.service;

import com.agro.wallet.entities.WalletEntity;

public interface WalletEntityService  extends BaseService<WalletEntity,Integer>{

    WalletEntity findbyUserId(String userId);

}
