package com.agro.wallet.impl;


import com.agro.wallet.LogoutService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.request.LogoutInput;
import com.agro.wallet.response.LogoutOutput;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class LogoutServiceImpl implements LogoutService {


    @Autowired
    LoginStore loginStore;

    @Override
    public LogoutOutput logoutUser(LogoutInput logoutInput) {

        log.info("start logout user");
        LoginData loginData = loginStore.getValue(logoutInput.getToken());

        if (StringUtils.isEmpty(loginData)) {
            throw new WalletException(ErrorCode.INVALID_REQUEST);
        }
        loginStore.remove(logoutInput.getToken());

        log.info("end logout user");
        return LogoutOutput.builder().message("You have been successfully logged out").build();
    }
}
