package com.agro.wallet;

import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.response.AddMoneyOutput;

public interface AddMoneyService {

    public AddMoneyOutput addMoney(AddMoneyInput addMoneyInput);

}
