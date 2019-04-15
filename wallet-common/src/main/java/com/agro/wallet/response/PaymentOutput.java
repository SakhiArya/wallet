package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.constants.TransactionStatus;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentOutput extends ApiResponse {

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Double amount;
    private String txnId;

    @Builder
    public PaymentOutput(ErrorCode errorCode, String errorMessage,
        String message, TransactionStatus transactionStatus, Double amount, String txnId) {
        super(errorCode, errorMessage, message);
        this.transactionStatus = transactionStatus;
        this.amount = amount;
        this.txnId = txnId;
    }
}
