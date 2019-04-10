package com.agro.wallet.impl;

import com.agro.wallet.OtpValidationService;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.response.SubmitOtpOutput;
import com.agro.wallet.utils.RegisterationTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class OtpValidationServiceImpl implements OtpValidationService {

    @Autowired
    private RegisterationTokenStore tokenStore;

    @Override
    public SubmitOtpOutput validateOtp(SubmitOtpInput submitOtpInput) {
         String storedOtp = tokenStore.getValue(submitOtpInput.getToken());
         if(StringUtils.isEmpty(storedOtp)){
             //TODO
         }
         if(storedOtp.equals(submitOtpInput.getOtp())){

             //update user status

             return SubmitOtpOutput.builder().message("Congratulations you are successfully "
                 + "registered please login with your mobile number and password").build();
         }

        return null;
    }
}
