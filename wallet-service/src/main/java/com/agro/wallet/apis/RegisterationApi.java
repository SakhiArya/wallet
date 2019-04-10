package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.RegisterationService;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.WalletRegistrationOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("registerationApi")
public class RegisterationApi extends ApiService<WalletRegisterationInput, WalletRegistrationOutput> {

    @Autowired
    RegisterationService registerationService;

    @Override
    public WalletRegistrationOutput callApi(WalletRegisterationInput input) {
        return registerationService.registerUser(input);
    }
}
