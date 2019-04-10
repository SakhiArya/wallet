package com.agro.wallet.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubmitOtpInput extends ApiRequest{

    @NotNull
    private String token;

    @NotNull
    private String otp;
}
