package com.agro.wallet;

import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.response.SubmitOtpOutput;

public interface OtpValidationService {

    public SubmitOtpOutput validateOtp(SubmitOtpInput submitOtpInput);

}
