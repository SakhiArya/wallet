package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WalletRegistrationOutput extends ApiResponse {

    private String token;

    @Builder
    public WalletRegistrationOutput(ErrorCode errorCode,
        String errorMessage, String message, String token) {
        super(errorCode, errorMessage, message);

        this.token = token;
    }

    public WalletRegistrationOutput() {
        super();
    }
}
