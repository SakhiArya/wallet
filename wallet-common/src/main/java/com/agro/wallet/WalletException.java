package com.agro.wallet;

import com.agro.wallet.constants.ErrorCode;
import lombok.Getter;
import lombok.Setter;

public class WalletException extends RuntimeException{


    @Getter
    @Setter
    private ErrorCode errorCode;

    @Getter
    @Setter
    private String errorMessage;

    public WalletException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }

    public WalletException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public WalletException() {
        super();
    }

}
