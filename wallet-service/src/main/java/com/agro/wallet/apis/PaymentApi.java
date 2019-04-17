package com.agro.wallet.apis;

import com.agro.wallet.ApiService;
import com.agro.wallet.PaymentService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.PaymentOutput;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("paymentApi")
public class PaymentApi extends ApiService<PaymentInput, PaymentOutput> {

    @Autowired
    PaymentService paymentService;

    @Autowired
    LoginStore loginStore;

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    WalletEntityService walletEntityService;

    @Override
    public PaymentOutput callApi(PaymentInput paymentInput) throws WalletException {
        UserEntity user = userEntityService.findByMobileNumber(paymentInput.getPayeeMobileNumber());
        LoginData loginData = loginStore.getValue(paymentInput.getToken());
        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(loginData)) {

            throw new WalletException(ErrorCode.INVALID_REQUEST);
        }
        WalletEntity payeeWallet = user.getWalletId();
        WalletEntity payerWallet = walletEntityService.findById(loginData.getWalletId());
        return paymentService
            .payment(paymentInput, payeeWallet, payerWallet, paymentInput.getTxnId());
    }
}
