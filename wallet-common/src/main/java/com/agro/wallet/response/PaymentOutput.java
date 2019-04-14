package com.agro.wallet.response;

import com.agro.wallet.constants.TransactionStatus;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;

@Builder
public class PaymentOutput extends ApiResponse {

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Double amount;
    private String txnId;
}
