package ApiTest;

import static org.junit.Assert.assertEquals;

import com.agro.wallet.WalletMain;
import com.agro.wallet.constants.C;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.request.Address;
import com.agro.wallet.request.LoginInput;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.LoginOutput;
import com.agro.wallet.response.SubmitOtpOutput;
import com.agro.wallet.response.WalletApiResponse;
import com.agro.wallet.response.WalletRegistrationOutput;
import com.agro.wallet.service.AddressEntityService;
import com.agro.wallet.service.LoginEntityService;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.JwtTokenUtil;
import com.agro.wallet.utils.RegisterationTokenStore;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Date;
import net.bytebuddy.asm.Advice.Unused;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WalletMain.class)
@WebAppConfiguration
@Transactional

/*This Base class tests registeration
    and
    submit otp flow along with that it creates test data for
    rest of the testApis*/
public class WalletApiBaseTest {

    protected static MockMvc mvc;
    protected String tokenUserOne;
    protected String mobileUserOne = "8586808559";
    protected String passwordUserOne = "qwerty@123";
    protected String tokenUserTwo;
    protected String mobileUserTwo = "9980998990";
    protected String passwordUserTwo = "qwerty@123" ;
    protected String loginUri = C.CONTROLLER_USER+C.API_LOGIN;
    protected String registerationUri = C.CONTROLLER_USER+C.API_REGISTERATION;
    protected String submitUri = C.CONTROLLER_USER+C.API_SUBMITOTP;
    protected String addMoneyUri = C.CONTROLLER_WALLET+C.API_ADDMONEY;
    protected String cancelTransactionsUri = C.CONTROLLER_WALLET+C.API_CANCEL_TXNS;
    protected static String getPayeeNameUri = C.CONTROLLER_WALLET+C.API_FETCH_PAYEE_NAME;
    protected String paymentUri = C.CONTROLLER_WALLET+C.API_PAYMENT;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private RegisterationTokenStore tokenStore;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserEntityService userEntityService;

    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        //Registeration for user 1
        String inputJsonForRegisteration = mapToJson(getUserOneForRegisteration());

        MvcResult mvcResultForRegisteration = getMvcResult(registerationUri,inputJsonForRegisteration);

        String registerationResponse = mvcResultForRegisteration.getResponse().getContentAsString();

        WalletApiResponse<WalletRegistrationOutput> walletApiResponseT =  mapFromJson
            (registerationResponse, new
            TypeReference<WalletApiResponse<WalletRegistrationOutput>>(){});

        WalletRegistrationOutput walletApiResponse = walletApiResponseT.getResult();

        int registerationStatus = mvcResultForRegisteration.getResponse().getStatus();
        //assert status
        assertEquals(200, registerationStatus);

        String otpForSubmitOtpApi = tokenStore.getValue(walletApiResponse.getToken());
        String tokenForSubmitOtpApi = walletApiResponse.getToken();

        //submitOtpForUser1

        String inputJsonForSubmitOtp = mapToJson(getSubmitRequestForValidatingOTP
            (otpForSubmitOtpApi,tokenForSubmitOtpApi,mobileUserOne));

        MvcResult mvcResultForSubmitOtp = getMvcResult(submitUri,inputJsonForSubmitOtp);

        String submitOtpResponse = mvcResultForSubmitOtp.getResponse().getContentAsString();

        int submitOtpStatus = mvcResultForRegisteration.getResponse().getStatus();
        //assert status
        assertEquals(200, submitOtpStatus);

        UserEntity ifUserPresent = userEntityService.findIfUserAlreadyExists
            (getUserOneForRegisteration().getMobileNumber());

        if (null!=ifUserPresent && null != ifUserPresent.getMobileNumber())
        assertEquals(mobileUserOne, ifUserPresent.getMobileNumber());


        //loginOTp for user 1

        String inputJson = mapToJson(getUserOne());
        MvcResult mvcResultForLogin = getMvcResult(loginUri,inputJson);

        int status = mvcResultForLogin.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResultForLogin.getResponse().getContentAsString();

        WalletApiResponse<LoginOutput> loginApiResponse =  mapFromJson(content, new TypeReference<WalletApiResponse<LoginOutput>>(){});

        tokenUserOne = loginApiResponse.getResult().getToken();




        //Registeration for user 2
        String inputJsonForRegisteration2 = mapToJson(getUserTwoForRegisteration());

        MvcResult mvcResultForRegisteratingUser2 = getMvcResult(registerationUri,
            inputJsonForRegisteration2);

        String registerationResponseForuser2 = mvcResultForRegisteratingUser2.getResponse()
            .getContentAsString();

        WalletApiResponse<WalletRegistrationOutput> walletApiResponseforUser2 =  mapFromJson
            (registerationResponseForuser2, new
            TypeReference<WalletApiResponse<WalletRegistrationOutput>>(){});

        WalletRegistrationOutput walletRegistrationOutput = walletApiResponseforUser2.getResult();

        int registerationStatusForUser2 = mvcResultForRegisteratingUser2.getResponse().getStatus();
        //assert status
        assertEquals(200, registerationStatusForUser2);

        String otpForSubmitOtpApiUser2 = tokenStore.getValue(walletRegistrationOutput.getToken());
        String tokenForSubmitOtpApiUser2 = walletRegistrationOutput.getToken();

        //submitOtpForUser2

        String inputJsonForSubmitOtpUser2 = mapToJson(getSubmitRequestForValidatingOTP
            (otpForSubmitOtpApiUser2,tokenForSubmitOtpApiUser2,mobileUserTwo));

        MvcResult mvcResultForSubmitOtpUser2 = getMvcResult(submitUri,inputJsonForSubmitOtpUser2);

        String submitOtpResponseUser2 = mvcResultForSubmitOtpUser2.getResponse()
            .getContentAsString();

        int submitOtpStatusUser2 = mvcResultForRegisteration.getResponse().getStatus();
        //assert status
        assertEquals(200, submitOtpStatusUser2);

        UserEntity ifUser2Present = userEntityService.findIfUserAlreadyExists
            (getUserTwoForRegisteration().getMobileNumber());

        assertEquals(mobileUserTwo, ifUser2Present.getMobileNumber());


        //loginOTp for user 2

        inputJson = mapToJson(getUserTwo());
        mvcResultForLogin = getMvcResult(loginUri,inputJson);

        status = mvcResultForLogin.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResultForLogin.getResponse().getContentAsString();

        loginApiResponse =  mapFromJson(content, new TypeReference<WalletApiResponse<LoginOutput>>(){});
        tokenUserTwo = loginApiResponse.getResult().getToken();

    }
    protected static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected static <T> T mapFromJson(String json, TypeReference valueTypeRef)
        throws  IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, valueTypeRef);
    }

    protected WalletRegisterationInput getUserOneForRegisteration(){
        Address address = Address.builder().addressLine("Agro @pune").city("pune").country("IN")
            .pincode("411014").state("MH").build();
        WalletRegisterationInput walletRegisterationInput = WalletRegisterationInput.builder()
            .address(address).password(passwordUserOne).firstName("Sakhi").lastName("Arya")
            .emailId("sakhiarya009@gmail.com")
            .dob(new Date(04-06-1992))
            .displayName("sakhi").mobileNumber(mobileUserOne)
            .build();

        return walletRegisterationInput;
    }
    protected WalletRegisterationInput getUserTwoForRegisteration(){
        Address address = Address.builder().addressLine("Agro @pune").city("pune").country("IN")
            .pincode("411014").state("MH").build();
        WalletRegisterationInput walletRegisterationInput = WalletRegisterationInput.builder()
            .address(address).password(passwordUserTwo).firstName("Sakhi").lastName("Arya")
            .displayName("user2").mobileNumber(mobileUserTwo)
            .emailId("sakhi.a@eze")
            .dob(new Date(04-10-1990))
            .build();

        return walletRegisterationInput;
    }

    protected SubmitOtpInput getSubmitRequestForValidatingOTP(String otp,String token,String
        mobileNumber){

        SubmitOtpInput submitOtpInput = SubmitOtpInput.builder().mobileNumber(mobileNumber).otp(otp).token(token).build();

        return submitOtpInput;
    }

    protected LoginInput getUserOne(){
        LoginInput loginInput = new LoginInput();
        loginInput.setMobileNumber(mobileUserOne);
        loginInput.setPassword(passwordUserOne);
        return loginInput;
    }
    protected LoginInput getUserTwo(){
        LoginInput loginInput = new LoginInput();
        loginInput.setMobileNumber(mobileUserTwo);
        loginInput.setPassword(passwordUserTwo);
        return loginInput;
    }
    protected static MvcResult getMvcResult(String uri, String inputJson) throws Exception{
        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    }

    @Autowired
    LoginEntityService loginEntityService;

    @Autowired
    AddressEntityService addressEntityService;

    @Autowired
    WalletEntityService walletEntityService;

    @After
    public void cleanUp() throws Exception{

        loginEntityService.deleteByMobileNumber(mobileUserOne);
        loginEntityService.deleteByMobileNumber(mobileUserTwo);
        loginEntityService.getDao().flush();
        UserEntity userEntityUser1 = userEntityService.findByMobileNumber(mobileUserOne);
        UserEntity userEntityUser2 = userEntityService.findByMobileNumber(mobileUserTwo);

        userEntityService.deleteByMobileNumber(mobileUserOne);
        userEntityService.deleteByMobileNumber(mobileUserTwo);
        userEntityService.getDao().flush();
        if(null!=userEntityUser1 && null !=userEntityUser1.getAddressId()
            && null!=userEntityUser1.getAddressId().getAddressId())
        addressEntityService.deleteByAddressId(userEntityUser1.getAddressId().getAddressId());

        if(null!=userEntityUser2 && null !=userEntityUser2.getAddressId()
            && null!=userEntityUser2.getAddressId().getAddressId())
        addressEntityService.deleteByAddressId(userEntityUser2.getAddressId().getAddressId());

        addressEntityService.getDao().flush();

        if(null!=userEntityUser1 && null !=userEntityUser1.getWalletId()
            && null!=userEntityUser1.getWalletId().getWalletId())
        walletEntityService.deleteByWalletId(userEntityUser1.getWalletId().getWalletId());

        if(null!=userEntityUser2 && null !=userEntityUser2.getWalletId()
            && null!=userEntityUser2.getWalletId().getWalletId())
        walletEntityService.deleteByWalletId(userEntityUser2.getWalletId().getWalletId());
        walletEntityService.getDao().flush();

    }

    @Test
    public void dummy(){}


}
