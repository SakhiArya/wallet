package com.agro.wallet.response;


import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitOtpOutput extends ApiResponse {

    public SubmitOtpOutput() {
    }

    private String message;

    private String userId;


    public SubmitOtpOutput(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }

    @Builder
    public SubmitOtpOutput(ErrorCode errorCode, String errorMessage, String message,
        String userId) {
        super(errorCode, errorMessage);
        this.message = message;
        this.userId = userId;
    }

}
