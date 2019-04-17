package com.agro.wallet;

import com.agro.wallet.request.WalletBalaceInput;
import com.agro.wallet.response.WalletBalanceOutput;

public interface WalletBalanceService {

    WalletBalanceOutput getBalance(WalletBalaceInput walletRegisterationInput);

}
