package com.agro.wallet.service.impl;


import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.repository.WalletRepository;
import com.agro.wallet.service.WalletEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl extends
    BaseServiceImpl<WalletEntity, Integer> implements
    WalletEntityService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public JpaRepository<WalletEntity, Integer> getDao() {
        return walletRepository;
    }
}
