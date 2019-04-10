package com.agro.wallet.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitOtpInput extends ApiRequest{

    private String token;

    private String otp;
}
