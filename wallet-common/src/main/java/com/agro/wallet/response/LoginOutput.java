package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginOutput extends ApiResponse {

    private String token;

    @Builder
    public LoginOutput(ErrorCode errorCode, String errorMessage, String message, String token) {
        super(errorCode, errorMessage, message);
        this.token = token;
    }
}
