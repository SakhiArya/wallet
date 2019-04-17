package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.PaymentService;
import com.agro.wallet.WalletException;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.FetchUserOutput;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("payeeNameApi")
public class PayeeNameApi extends ApiService<PaymentInput, FetchUserOutput> {

    @Autowired
    PaymentService paymentService;

    @Autowired
    LoginStore loginStore;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    WalletEntityService walletEntityService;

    @Override
    public FetchUserOutput callApi(PaymentInput paymentInput) throws WalletException {
        UserEntity user = userEntityService.findByMobileNumber(paymentInput.getPayeeMobileNumber());
        LoginData loginData = loginStore.getValue(paymentInput.getToken());
        WalletEntity payeeWallet = user.getWalletId();
        WalletEntity payerWallet = walletEntityService.findById(loginData.getWalletId());
        TransactionEntity transaction = paymentService
            .createTransaction(paymentInput, payeeWallet, payerWallet);
        return FetchUserOutput.builder().amount(paymentInput.getAmount())
            .firstName(user.getFirstName()).lastName(user.getLastName()).
                displayName(user.getDisplayName()).txnId(transaction.getTxnId()).build();
    }
}
