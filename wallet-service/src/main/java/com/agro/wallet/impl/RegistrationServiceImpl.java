package com.agro.wallet.impl;

import com.agro.wallet.RegisterationService;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.WalletRegistrationOutput;
import com.agro.wallet.utils.OtpUtil;
import com.agro.wallet.utils.RegisterationTokenStore;
import com.agro.wallet.utils.TokenUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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


    @Value("${messageTemplate:your otp is}")
    private String messageTemplate;



    @Autowired
    private RegisterationTokenStore tokenStore;


    private static final Gson gson = new Gson();

    @Override
    public WalletRegistrationOutput registerUser(
        WalletRegisterationInput walletRegisterationInput) {

        //Todo :check if user already exists

        String JWT = Jwts.builder().setSubject(gson.toJson(walletRegisterationInput))
            .setExpiration(new Date(System.currentTimeMillis() + TokenUtils.EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, TokenUtils.SECRET).compact();
        String otp = generateOTP();
        String success = otpUtil.sendCampaign(apiKey,secretKey,useType,walletRegisterationInput
                .getMobileNumber()
                .toString(),
            messageTemplate+otp,senderId);
        registerUser(JWT,otp);


        return WalletRegistrationOutput.builder().token(JWT).isSuccess(Boolean.TRUE).build();
    }

    @Override
    public void registerUser(String token, String otp) {

        tokenStore.put(token, otp);

    }


    private String generateOTP() {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[4];

        for(int i = 0; i< otp.length ; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp.toString();
    }
}
