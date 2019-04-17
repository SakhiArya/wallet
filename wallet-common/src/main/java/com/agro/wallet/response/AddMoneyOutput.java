package com.agro.wallet.response;


import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AddMoneyOutput extends ApiResponse {

    private String newBalance;

    private String txnId;

    @Builder
    public AddMoneyOutput(ErrorCode errorCode, String errorMessage,
        String message, String newBalance,String txnId) {
        super(errorCode, errorMessage, message);
        this.newBalance = newBalance;
        this.txnId=txnId;
    }
}
