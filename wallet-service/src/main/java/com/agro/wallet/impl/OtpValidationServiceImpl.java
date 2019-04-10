package com.agro.wallet.impl;

import com.agro.wallet.OtpValidationService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.CurrencyType;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.constants.UserStatus;
import com.agro.wallet.constants.UserType;
import com.agro.wallet.entities.AddressEntity;
import com.agro.wallet.entities.LoginEntity;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.SubmitOtpOutput;
import com.agro.wallet.service.AddressEntityService;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.CommonUtils;
import com.agro.wallet.utils.RegisterationTokenStore;
import com.agro.wallet.utils.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class OtpValidationServiceImpl implements OtpValidationService {

    @Autowired
    private RegisterationTokenStore tokenStore;

    @Autowired
    private UserStore userStore;

    @Autowired
    private AddressEntityService addressEntityService;

    @Autowired
    private WalletEntityService walletEntityService;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public SubmitOtpOutput validateOtp(SubmitOtpInput submitOtpInput) {
        String token = submitOtpInput.getToken();
         String storedOtp = tokenStore.getValue(token);
        WalletRegisterationInput walletRegisterationInput =userStore.getValue(token);
         if(StringUtils.isEmpty(storedOtp) || StringUtils.isEmpty(walletRegisterationInput)){
             throw new WalletException(ErrorCode.INVALID_TOKEN);
         }
         if(storedOtp.equals(submitOtpInput.getOtp())){

            String userId=saveUserInDb(walletRegisterationInput);
             tokenStore.remove(token);
             userStore.remove(token);

             return SubmitOtpOutput.builder().message("Congratulations you are successfully "
                 + "registered please login with your mobile number and password").userId(userId)
                 .build();
         }

        return new SubmitOtpOutput(ErrorCode.WALLET_EXCEPTION,ErrorCode.WALLET_EXCEPTION
            .getDescription(),"Unable to save user",null);
    }


    @Transactional
    private String saveUserInDb(WalletRegisterationInput walletRegisterationInput){

        AddressEntity addressEntity = AddressEntity.builder()
            .addressLine1(walletRegisterationInput.getAddress().getAddressLine())
            .city(walletRegisterationInput.getAddress().getCity())
            .country(walletRegisterationInput.getAddress().getCountry())
            .state(walletRegisterationInput.getAddress().getState())
            .pincode(null!=walletRegisterationInput.getAddress().getPincode()?walletRegisterationInput
                .getAddress().getPincode():null)
            .addressId(commonUtils.generateUUID(walletRegisterationInput.getAddress().getCountry()))
            .build();

        AddressEntity savedEntity = addressEntityService.getDao().save(addressEntity);

        Double initialBalance = 0.0;
        WalletEntity walletEntity = WalletEntity.builder().balance(initialBalance)
            .currency(CurrencyType.INR)
            .walletId(commonUtils.generateUUID(walletRegisterationInput.getMobileNumber()))
            .build();

        WalletEntity savedWalletEntity=walletEntityService.getDao().save(walletEntity);

        UserEntity userEntity = UserEntity.builder()
            .userStatus(UserStatus.VERIFIED)
            .userType(UserType.PERSON)
            .addressId(savedEntity.getAddressId())
            .dob(walletRegisterationInput.getDob())
            .email(walletRegisterationInput.getEmailId())
            .firstName(walletRegisterationInput.getFirstName())
            .lastName(null!=walletRegisterationInput.getLastName()?walletRegisterationInput
                .getLastName():null)
            .userId(commonUtils.generateUUID(walletRegisterationInput.getFirstName()))
            .mobileNumber(walletRegisterationInput.getMobileNumber())
            .walletId(savedWalletEntity.getWalletId()).build();


        LoginEntity loginEntity = LoginEntity.builder().mobileNumber(walletRegisterationInput
            .getMobileNumber()).password(walletRegisterationInput.getPassword()).userId
            (userEntity.getUserId()).build();

        userEntity=userEntityService.getDao().save(userEntity);

        return userEntity.getUserId();


    }
}
