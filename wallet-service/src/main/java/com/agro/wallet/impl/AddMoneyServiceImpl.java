package com.agro.wallet.impl;


import com.agro.wallet.AddMoneyService;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.response.AddMoneyOutput;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import com.agro.wallet.utils.UserStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddMoneyServiceImpl implements AddMoneyService {

    @Autowired
    WalletEntityService walletEntityService;

    @Autowired
    LoginStore loginStore;

    @Override
    public AddMoneyOutput addMoney(AddMoneyInput addMoneyInput) {
        LoginData loginData = loginStore.getValue(addMoneyInput.getToken());
        WalletEntity walletEntity = walletEntityService.findById(loginData.getWalletId());
        Double currentBalance = walletEntity.getBalance();
        Double updatedBalance = currentBalance+addMoneyInput.getAmount();
        walletEntity.setBalance(updatedBalance);
        walletEntityService.getDao().save(walletEntity);
        return AddMoneyOutput.builder().newBalance("Your updated balance is "+updatedBalance).build();
    }
}
