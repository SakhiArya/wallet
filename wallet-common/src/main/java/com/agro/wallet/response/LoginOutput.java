package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOutput extends ApiResponse {

    private String token;

    public LoginOutput() {
    }

    @Builder
    public LoginOutput(ErrorCode errorCode, String errorMessage,String message,String token) {
        super(errorCode, errorMessage,message);
        this.token=token;
    }
}
