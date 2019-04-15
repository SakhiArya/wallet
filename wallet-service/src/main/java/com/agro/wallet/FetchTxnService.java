package com.agro.wallet;

import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.request.FetchTxnInput;
import com.agro.wallet.response.FetchTxnOutput;

public interface FetchTxnService {

    FetchTxnOutput fetchTxn(FetchTxnInput fetchTxnInput);
    TransactionEntity fetchOne(String txnId);

}
