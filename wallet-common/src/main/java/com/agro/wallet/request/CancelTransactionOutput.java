package com.agro.wallet.request;

import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelTransactionOutput extends ApiResponse {
        private String txnId;
        private TransactionStatus origTransactionStatus;
        private TransactionStatus finalTransactionStatus;
}
