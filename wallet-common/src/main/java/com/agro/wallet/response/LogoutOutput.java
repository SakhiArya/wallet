package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LogoutOutput extends ApiResponse {


    private String message;

    @Builder
    public LogoutOutput(String message) {
        this.message = message;
    }

    public LogoutOutput(ErrorCode errorCode, String errorMessage,
        String message, String message1) {
        super(errorCode, errorMessage, message);
        this.message = message1;
    }


}
