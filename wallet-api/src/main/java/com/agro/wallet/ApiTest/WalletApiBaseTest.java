package com.agro.wallet.ApiTest;

import static org.junit.Assert.assertEquals;

import com.agro.wallet.WalletMain;
import com.agro.wallet.constants.C;
import com.agro.wallet.request.LoginInput;
import com.agro.wallet.response.LoginOutput;
import com.agro.wallet.response.WalletApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import org.junit.Before;
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
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WalletMain.class)
@WebAppConfiguration
public class WalletApiBaseTest {

    protected MockMvc mvc;
    protected String tokenUserOne;
    protected String mobileUserOne = "8586808559";
    protected String passwordUserOne = "qwerty@123";
    protected String tokenUserTwo;
    protected String mobileUserTwo = "9980998990";
    protected String passwordUserTwo = "qwerty@123" ;
    protected String loginUri = C.CONTROLLER_USER+C.API_LOGIN;
    protected String addMoneyUri = C.CONTROLLER_WALLET+C.API_ADDMONEY;
    protected String cancelTransactionsUri = C.CONTROLLER_WALLET+C.API_CANCEL_TXNS;
    protected String getPayeeNameUri = C.CONTROLLER_WALLET+C.API_FETCH_PAYEE_NAME;
    protected String paymentUri = C.CONTROLLER_WALLET+C.API_PAYMENT;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String inputJson = mapToJson(getUserOne());
        MvcResult mvcResult = getMvcResult(loginUri,inputJson);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        WalletApiResponse<LoginOutput> walletApiResponse =  mapFromJson(content, new TypeReference<WalletApiResponse<LoginOutput>>(){});

        tokenUserOne = walletApiResponse.getResult().getToken();


        inputJson = mapToJson(getUserTwo());
        mvcResult = getMvcResult(loginUri,inputJson);

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();

        walletApiResponse =  mapFromJson(content, new TypeReference<WalletApiResponse<LoginOutput>>(){});
        tokenUserTwo = walletApiResponse.getResult().getToken();

    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
        throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
    protected <T> T mapFromJson(String json, TypeReference valueTypeRef)
        throws  IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, valueTypeRef);
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
    protected MvcResult getMvcResult(String uri,String inputJson) throws Exception{
        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    }
}
