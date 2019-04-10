package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.response.SubmitOtpOutput;
import org.springframework.stereotype.Service;

@Service("otpValidationApi")
public class OtpValidationApi extends ApiService<SubmitOtpInput, SubmitOtpOutput> {

    @Override
    public SubmitOtpOutput callApi(SubmitOtpInput input) {
        return null;
    }
}
