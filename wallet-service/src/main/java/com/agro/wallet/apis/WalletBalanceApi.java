package com.agro.wallet.apis;


import com.agro.wallet.ApiService;
import com.agro.wallet.WalletBalanceService;
import com.agro.wallet.WalletException;
import com.agro.wallet.request.WalletBalaceInput;
import com.agro.wallet.response.WalletBalanceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getBalaceApi")
public class WalletBalanceApi extends ApiService<WalletBalaceInput, WalletBalanceOutput> {

    @Autowired
    WalletBalanceService walletBalanceService;

    @Override
    public WalletBalanceOutput callApi(WalletBalaceInput input) throws WalletException {
        return walletBalanceService.getBalance(input);
    }
}
