package com.agro.wallet.impl;

import com.agro.wallet.LoginService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.entities.LoginEntity;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.LoginInput;
import com.agro.wallet.response.LoginOutput;
import com.agro.wallet.service.LoginEntityService;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.JwtTokenUtil;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import com.agro.wallet.utils.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginStore loginStore;

    @Autowired
    LoginEntityService loginEntityService;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginOutput loginUser(LoginInput loginInput) {

        LoginEntity loginEntity = loginEntityService.findByMobileNumber(loginInput
            .getMobileNumber());
        if(StringUtils.isEmpty(loginEntity)){
            throw new WalletException(ErrorCode.INVALID_USER);
        }
        UserEntity userEntity = userEntityService.findByUserId(loginEntity.getUserId());


        if (!loginInput.getPassword().equals(loginEntity.getPassword()))
            throw new WalletException(ErrorCode.INVALID_PASSWORD);

        LoginData loginData = LoginData.builder().mobileNumber(loginEntity.getMobileNumber()).walletId
            (userEntity.getWalletId())
            .build();

        String token = jwtTokenUtil.generateJWT(loginData);
        loginStore.put(token,loginData);

        return LoginOutput.builder().message("Successfully logged In").build();
    }
}
