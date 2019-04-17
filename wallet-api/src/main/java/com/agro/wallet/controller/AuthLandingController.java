package com.agro.wallet.controller;


import com.agro.wallet.apis.LoginApi;
import com.agro.wallet.apis.LogoutApi;
import com.agro.wallet.apis.OtpValidationApi;
import com.agro.wallet.apis.RegisterationApi;
import com.agro.wallet.constants.C;
import com.agro.wallet.request.LoginInput;
import com.agro.wallet.request.LogoutInput;
import com.agro.wallet.request.ResponseUtils;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.request.WalletBalaceInput;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.WalletApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =C.CONTROLLER_USER)
@Api(value = "", description = "this controller is for user's registration and login ")
@Slf4j
public class AuthLandingController {

    @Autowired
    RegisterationApi registerationApi;

    @Autowired
    OtpValidationApi otpValidationApi;

    @Autowired
    LoginApi loginApi;


    @Autowired
    private LogoutApi logoutApi;


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to register user", notes = "The API is used to create/register new user",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_REGISTERATION)

    public WalletApiResponse registerUser(@Valid @RequestBody WalletRegisterationInput
        registerationInput) {
        log.info("Inside AuthLandingController ,Start of registerUser with mobile number {}",
            registerationInput.getMobileNumber
            ());

        return ResponseUtils.successResponse(registerationApi.execute(registerationInput));
    }


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to verify user", notes = "The API is used to validate OTP",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_SUBMITOTP)

    public WalletApiResponse submitOtp(@Valid @RequestBody SubmitOtpInput
        submitOtpInput) {
        log.info("Inside AuthLandingController ,Start of submitOtp" );

        return ResponseUtils.successResponse(otpValidationApi.execute(submitOtpInput));
    }


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to login user", notes = "The API is used  for login ",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_LOGIN)

    public WalletApiResponse login(@Valid @RequestBody LoginInput
        loginInput) {

        log.info("Inside AuthLandingController ,Start of login with mobile number {} ", loginInput.getMobileNumber
            ());
        return ResponseUtils.successResponse(loginApi.execute(loginInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to logout", notes = "The API is used to logout from wallet",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})

    @RequestMapping(method = RequestMethod.POST, value = C.API_LOGOUT)

    public WalletApiResponse logout(@Valid @RequestBody LogoutInput
        logoutInput) {
        log.info("Inside logout ,AuthLandingController");

        return ResponseUtils.successResponse(logoutApi.execute(logoutInput));
    }


}
