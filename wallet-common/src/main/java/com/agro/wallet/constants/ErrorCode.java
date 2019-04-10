package com.agro.wallet.constants;

import lombok.Getter;

public enum ErrorCode {
    UNKNOWN_EXCEPTION("Exception is unknown"),
    INVALID_INPUT("Invalid input"),
    SQL_EXCEPTION("Data Integrity Exception"),
    WALLET_EXCEPTION("Wallet exception occured"),
    INVALID_TOKEN("Invalid Request"),
    ALREADY_EXISTS("Your Mobile Number is already registered with us kindly login ,if you have "
        + "forgoten password please click on \"forget password\"")

    ;

    @Getter
    private String description;

    ErrorCode(String description) {
        this.description = description;
    }
}
