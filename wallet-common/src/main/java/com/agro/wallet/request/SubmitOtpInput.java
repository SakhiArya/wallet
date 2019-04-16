package com.agro.wallet.request;

import javax.validation.constraints.NotNull;
import lombok.Builder;
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

    @Builder
    public SubmitOtpInput(@NotNull String token,
        @NotNull String otp) {
        this.token = token;
        this.otp = otp;
    }

    public SubmitOtpInput(String mobileNumber,
        @NotNull String token, @NotNull String otp) {
        super(mobileNumber);
        this.token = token;
        this.otp = otp;
    }

    public SubmitOtpInput(String mobileNumber, String token,
        @NotNull String token1, @NotNull String otp) {
        super(mobileNumber, token);
        this.token = token1;
        this.otp = otp;
    }

}
