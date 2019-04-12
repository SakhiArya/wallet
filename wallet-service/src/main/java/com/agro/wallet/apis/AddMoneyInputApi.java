package com.agro.wallet.apis;

import com.agro.wallet.AddMoneyService;
import com.agro.wallet.ApiService;
import com.agro.wallet.WalletException;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.response.AddMoneyOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addMoneyApi")
public class AddMoneyInputApi extends ApiService<AddMoneyInput, AddMoneyOutput> {

    @Autowired
    AddMoneyService addMoneyService;

    @Override
    public AddMoneyOutput callApi(AddMoneyInput input) throws WalletException {
        return addMoneyService.addMoney(input);
    }
}
