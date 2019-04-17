package com.agro.wallet.response;


import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubmitOtpOutput extends ApiResponse {

    private String mobileNumber;


    public SubmitOtpOutput(String message, String userId) {
        this.mobileNumber = userId;
    }

    @Builder
    public SubmitOtpOutput(ErrorCode errorCode, String errorMessage, String message,
        String mobileNumber) {
        super(errorCode, errorMessage, message);
        this.mobileNumber = mobileNumber;
    }

}
