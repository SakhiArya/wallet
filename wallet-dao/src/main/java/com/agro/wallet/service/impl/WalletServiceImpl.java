package com.agro.wallet.service.impl;


import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.repository.WalletRepository;
import com.agro.wallet.service.WalletEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl extends
    BaseServiceImpl<WalletEntity, Integer> implements
    WalletEntityService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public WalletRepository getDao() {
        return walletRepository;
    }

    @Override
    public WalletEntity findById(String walletId) {

        return getDao().findByWalletId(walletId);
    }

    @Override
    public void deleteByWalletId(String walletId) {
        getDao().deleteByWalletId(walletId);
    }

    @Override
    public WalletEntity findByWalletId(String walletId) {
        return getDao().findByWalletId(walletId);
    }
}
