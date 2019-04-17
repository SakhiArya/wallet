package com.agro.wallet;

import com.agro.wallet.request.LoginInput;
import com.agro.wallet.response.LoginOutput;

public interface LoginService {

    public LoginOutput loginUser(LoginInput loginInput);

}
