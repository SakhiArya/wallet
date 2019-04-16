package com.agro.wallet.impl;

import com.agro.wallet.WalletBalanceService;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.WalletBalaceInput;
import com.agro.wallet.response.WalletBalanceOutput;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletBalanceServiceImpl  implements WalletBalanceService {

    @Autowired
    WalletEntityService walletEntityService;

    @Autowired
    LoginStore loginStore;

    @Override
    public WalletBalanceOutput getBalance(WalletBalaceInput walletRegisterationInput) {

        LoginData loginData = loginStore.getValue(walletRegisterationInput.getToken());

        WalletEntity walletEntity = walletEntityService.findByWalletId(loginData.getWalletId());

        return WalletBalanceOutput.builder().availabaleBalance(walletEntity.getBalance()).build();
    }
}
