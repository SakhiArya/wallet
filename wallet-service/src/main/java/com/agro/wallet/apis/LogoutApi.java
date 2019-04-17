package com.agro.wallet.apis;


import com.agro.wallet.ApiService;
import com.agro.wallet.LogoutService;
import com.agro.wallet.request.LogoutInput;
import com.agro.wallet.response.LogoutOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("logoutApi")
public class LogoutApi extends ApiService<LogoutInput, LogoutOutput> {


    @Autowired
    LogoutService logoutService;

    @Override
    public LogoutOutput callApi(LogoutInput input) {
        return logoutService.logoutUser(input);
    }
}
