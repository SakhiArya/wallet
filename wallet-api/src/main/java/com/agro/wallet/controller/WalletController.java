package com.agro.wallet.controller;


import com.agro.wallet.apis.AddMoneyInputApi;
import com.agro.wallet.constants.C;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.request.ResponseUtils;
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
@RequestMapping(value ="/")
@Api(value = "", description = "this controller is for wallet transactions ")
@Slf4j
public class WalletController {

    @Autowired
    AddMoneyInputApi addMoneyInputApi;


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to register user", notes = "The API is used to create/register new user",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_ADDMONEY)

    public WalletApiResponse addMoney(@Valid @RequestBody AddMoneyInput
        addMoneyInput) {
        log.info("Inside addMoney ,Start of addMoney");

        return ResponseUtils.successResponse(addMoneyInputApi.execute(addMoneyInput));
    }

}
