package com.agro.wallet.impl;

import com.agro.wallet.LoginService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.entities.LoginEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.LoginInput;
import com.agro.wallet.response.LoginOutput;
import com.agro.wallet.service.LoginEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.JwtTokenUtil;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import com.agro.wallet.utils.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginStore loginStore;

    @Autowired
    LoginEntityService loginEntityService;

    @Autowired
    WalletEntityService walletEntityService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginOutput loginUser(LoginInput loginInput) {

        LoginEntity loginEntity = loginEntityService.findByMobileNumber(loginInput
            .getMobileNumber());
        WalletEntity walletEntity = walletEntityService.findbyUserId(loginEntity.getUserId());
        LoginData loginData = LoginData.builder().userId(loginEntity.getUserId()).walletId
            (walletEntity.getWalletId())
            .build();
        String token = jwtTokenUtil.generateJWT(loginData);
        loginStore.put(token,loginData);
        if (!loginInput.equals(loginEntity.getPassword()))
            throw new WalletException(ErrorCode.INVALID_PASSWORD);

        return LoginOutput.builder().message("loggedInsuccessfully").build();
    }
}
