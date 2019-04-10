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

    public SubmitOtpOutput(String message) {
        this.message = message;
    }

    @Builder
    public SubmitOtpOutput(ErrorCode errorCode, String errorMessage,
        String message) {
        super(errorCode, errorMessage);
        this.message = message;
    }
}
