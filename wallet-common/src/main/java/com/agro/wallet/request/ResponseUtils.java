package com.agro.wallet.request;

import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.response.ApiResponse;
import com.agro.wallet.response.WalletApiResponse;

public class ResponseUtils {

    public static WalletApiResponse failResponse(ErrorCode errorCode,
        String errorMessage) {
        WalletApiResponse output = new WalletApiResponse();
        output.setSuccess(Boolean.FALSE);
        output.setErrorCode(errorCode);
        output.setErrorMessage(errorMessage);
        return output;
    }

    public static <T extends ApiResponse> WalletApiResponse successResponse(T obj) {
        WalletApiResponse output = new WalletApiResponse();
        output.setSuccess(Boolean.TRUE);
        output.setResult(obj);
        return output;
    }
}
