package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FetchUserOutput extends ApiResponse {

    private String firstName;
    private String lastName;
    private String displayName;
    private Double amount;
    private String txnId;

    @Builder
    public FetchUserOutput(ErrorCode errorCode, String errorMessage,
        String message, String firstName, String lastName, String displayName, Double amount,
        String txnId) {
        super(errorCode, errorMessage, message);
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.amount = amount;
        this.txnId = txnId;
    }
}
