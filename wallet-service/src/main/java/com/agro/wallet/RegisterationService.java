package com.agro.wallet;

import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.WalletRegistrationOutput;
import java.util.function.Predicate;

public interface RegisterationService {

    WalletRegistrationOutput registerUser(WalletRegisterationInput walletRegisterationInput);

    void registerUserInToken(String token, String otp,WalletRegisterationInput walletRegisterationInput);

    default <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

}
