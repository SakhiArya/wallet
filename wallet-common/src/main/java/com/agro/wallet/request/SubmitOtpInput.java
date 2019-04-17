package com.agro.wallet.request;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SubmitOtpInput extends ApiRequest {


    @NotNull
    private String otp;


    @Builder
    public SubmitOtpInput(String mobileNumber,
        @NotNull String token, @NotNull String otp) {
        super(mobileNumber, token);
        this.otp = otp;
    }


}
