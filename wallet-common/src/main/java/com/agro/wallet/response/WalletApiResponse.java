package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletApiResponse <T extends ApiResponse> {

    private boolean success = false;

    private ErrorCode errorCode;

    private String errorMessage;

    private T result;
}
