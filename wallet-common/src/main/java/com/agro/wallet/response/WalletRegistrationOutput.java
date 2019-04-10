package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRegistrationOutput extends ApiResponse {

    private Boolean isSuccess;

    private String message;

    private String token;

    @Builder
    public WalletRegistrationOutput(ErrorCode errorCode,
        String errorMessage, Boolean isSuccess, String message,String token) {
        super(errorCode, errorMessage);
        this.isSuccess = isSuccess;
        this.message = message;
        this.token=token;
    }

    public WalletRegistrationOutput(){
        super();
    }
}
