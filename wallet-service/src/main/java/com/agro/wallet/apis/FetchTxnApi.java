package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.FetchTxnService;
import com.agro.wallet.WalletException;
import com.agro.wallet.request.FetchTxnInput;
import com.agro.wallet.response.FetchTxnOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fetchTxnApi")
public class FetchTxnApi extends ApiService<FetchTxnInput, FetchTxnOutput> {

    @Autowired
    FetchTxnService fetchTxnService;

    @Override
    public FetchTxnOutput callApi(FetchTxnInput input) throws WalletException {
        return fetchTxnService.fetchTxn(input);
    }
}
