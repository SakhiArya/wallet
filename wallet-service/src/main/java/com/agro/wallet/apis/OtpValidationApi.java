package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.OtpValidationService;
import com.agro.wallet.RegisterationService;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.response.SubmitOtpOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("otpValidationApi")
public class OtpValidationApi extends ApiService<SubmitOtpInput, SubmitOtpOutput> {

    @Autowired
    OtpValidationService otpValidationService;

    @Override
    public SubmitOtpOutput callApi(SubmitOtpInput input) {
        return otpValidationService.validateOtp(input);
    }
}
