package com.agro.wallet.response;

import com.agro.wallet.constants.ErrorCode;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FetchTxnOutput extends ApiResponse {

    List<Transactions> transactions;

    List<ReceivedTransactions> receivedTransactions;

    @Builder
    public FetchTxnOutput(ErrorCode errorCode, String errorMessage,
        String message, List<Transactions> transactions,
        List<ReceivedTransactions> receivedTransactions) {
        super(errorCode, errorMessage, message);
        this.transactions = transactions;
        this.receivedTransactions = receivedTransactions;
    }
}
