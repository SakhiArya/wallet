package com.agro.wallet.impl;

import com.agro.wallet.RegisterationService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.WalletRegistrationOutput;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.utils.JwtTokenUtil;
import com.agro.wallet.utils.OtpUtil;
import com.agro.wallet.utils.RegisterationTokenStore;
import com.agro.wallet.utils.TokenUtils;
import com.agro.wallet.utils.UserStore;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Random;
import net.bytebuddy.asm.Advice.Unused;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RegistrationServiceImpl implements RegisterationService {


    @Value("${appkey:IFXRX4KHXLSCOX5524X8PQRAK2J1LX7L}")
    private String apiKey;

    @Value("${secretKey:IRL395HG4VPEXO6T}")
    private String secretKey;

    @Value("${useType:stage}")
    private String useType;

    @Value("${senderId:6393995876}")
    private String senderId;

    @Autowired
    private OtpUtil otpUtil;


    @Value("${messageTemplate:your otp for wallet registeration is: }")
    private String messageTemplate;

    @Autowired
    private UserEntityService userEntityService;



    @Autowired
    private RegisterationTokenStore tokenStore;

    @Autowired
    private UserStore userStore;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public WalletRegistrationOutput registerUser(
        WalletRegisterationInput walletRegisterationInput) {

        UserEntity ifUserPresent = userEntityService.findIfUserAlreadyExists
            (walletRegisterationInput.getMobileNumber());

        if(!StringUtils.isEmpty(ifUserPresent)){
            throw new WalletException(ErrorCode.ALREADY_EXISTS);
        }

        String JWT =jwtTokenUtil.generateJWT(walletRegisterationInput);
        String otp = generateOTP();
        String success = otpUtil.sendCampaign(apiKey,secretKey,useType,walletRegisterationInput
                .getMobileNumber()
                .toString(),
            messageTemplate+otp,senderId);
        registerUserInToken(JWT,otp,walletRegisterationInput);

        return WalletRegistrationOutput.builder().token(JWT).isSuccess(Boolean.TRUE).build();

    }

    @Override
    public void registerUserInToken(String token, String otp,WalletRegisterationInput walletRegisterationInput) {
        tokenStore.put(token, otp);
        userStore.put(token,walletRegisterationInput);

    }



    private String generateOTP() {
        String numbers = "1234567890";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for(int i = 0; i< 4; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
                return otp.toString() ;
    }
}
