package com.agro.wallet.controller;


import com.agro.wallet.apis.OtpValidationApi;
import com.agro.wallet.apis.RegisterationApi;
import com.agro.wallet.request.ResponseUtils;
import com.agro.wallet.request.SubmitOtpInput;
import com.agro.wallet.request.WalletRegisterationInput;
import com.agro.wallet.response.SubmitOtpOutput;
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
@RequestMapping(value = "/user")
@Api(value = "", description = "this controller is for user's registration and login ")
@Slf4j
public class AuthLandingController {

    @Autowired
    RegisterationApi registerationApi;

    @Autowired
    OtpValidationApi otpValidationApi;



    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to register user", notes = "The API is used to create/register new user",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = "/register")

    public WalletApiResponse createRule(@Valid @RequestBody WalletRegisterationInput registerationInput)
        throws Exception {

        return ResponseUtils.successResponse(registerationApi.execute(registerationInput));
    }


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to register user", notes = "The API is used to create/register new user",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = "/submitOtp")

    public WalletApiResponse submitOtp(@Valid @RequestBody SubmitOtpInput
        registerationInput)
        throws Exception {

        return ResponseUtils.successResponse(otpValidationApi.execute(registerationInput));
    }


}
