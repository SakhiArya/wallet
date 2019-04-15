package com.agro.wallet;

import com.agro.wallet.request.CancelTransactionInput;
import com.agro.wallet.request.CancelTransactionOutput;

public interface CancelTransactionService {

    CancelTransactionOutput cancelTransaction(CancelTransactionInput cancelTransactionInput);

}
