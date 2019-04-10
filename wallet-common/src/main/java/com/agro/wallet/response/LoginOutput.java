package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;

public class LoginOutput extends ApiResponse {

    public LoginOutput() {
    }

    @Builder
    public LoginOutput(ErrorCode errorCode, String errorMessage,String message) {
        super(errorCode, errorMessage,message);
    }
}
