package com.agro.wallet.controller;


import com.agro.wallet.apis.AddMoneyInputApi;
import com.agro.wallet.apis.PaymentApi;
import com.agro.wallet.constants.C;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.apis.FetchTxnApi;
import com.agro.wallet.request.FetchTxnInput;
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
    private AddMoneyInputApi addMoneyInputApi;

    @Autowired
    private PaymentApi paymentApi;

    @Autowired
    FetchTxnApi fetchTxnApi;


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to register user", notes = "The API is used to add money to the wallet",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_ADDMONEY)

    public WalletApiResponse addMoney(@Valid @RequestBody AddMoneyInput
        addMoneyInput) {
        log.info("Inside walletcontroller ,Start of addMoney");

        return ResponseUtils.successResponse(addMoneyInputApi.execute(addMoneyInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to register user", notes = "The API is used to create/register new user",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_PAYMENT)

    public WalletApiResponse payment(@Valid @RequestBody PaymentInput
        paymentInput) {
        log.info("Inside Pay , going to start Payment");

        return ResponseUtils.successResponse(paymentApi.execute(paymentInput));
    }

    @RequestMapping(method = RequestMethod.POST, value = C.API_FETCH_TXNS)

    public WalletApiResponse fetchTransactions(@Valid @RequestBody FetchTxnInput
        fetchTxnInput) {
        log.info("Inside walletcontroller ,Start of addMoney");

        return ResponseUtils.successResponse(fetchTxnApi.execute(fetchTxnInput));
    }




}
