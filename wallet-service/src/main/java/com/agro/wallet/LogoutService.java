package com.agro.wallet;

import com.agro.wallet.request.LogoutInput;
import com.agro.wallet.response.LogoutOutput;

public interface LogoutService {

    public LogoutOutput logoutUser(LogoutInput loginInput);

}
