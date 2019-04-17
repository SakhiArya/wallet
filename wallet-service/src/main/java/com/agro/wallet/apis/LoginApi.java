package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.LoginService;
import com.agro.wallet.WalletException;
import com.agro.wallet.request.LoginInput;
import com.agro.wallet.response.LoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginApi")
public class LoginApi extends ApiService<LoginInput, LoginOutput> {

    @Autowired
    LoginService loginService;

    @Override
    public LoginOutput callApi(LoginInput input) throws WalletException {
        return loginService.loginUser(input);
    }
}
