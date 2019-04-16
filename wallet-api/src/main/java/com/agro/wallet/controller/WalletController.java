package com.agro.wallet.controller;


import com.agro.wallet.apis.AddMoneyInputApi;
import com.agro.wallet.apis.CancelTransactionApi;
import com.agro.wallet.apis.PayeeNameApi;
import com.agro.wallet.apis.PaymentApi;
import com.agro.wallet.apis.WalletBalanceApi;
import com.agro.wallet.constants.C;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.request.CancelTransactionInput;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.apis.FetchTxnApi;
import com.agro.wallet.request.FetchTxnInput;
import com.agro.wallet.request.ResponseUtils;
import com.agro.wallet.request.WalletBalaceInput;
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
@RequestMapping(value =C.CONTROLLER_WALLET)
@Api(value = "All the basic wallet operations ", description = "this controller is for wallet transactions ")
@Slf4j
public class WalletController {

    @Autowired
    private AddMoneyInputApi addMoneyInputApi;

    @Autowired
    private PaymentApi paymentApi;

    @Autowired
    private PayeeNameApi payeeNameApi;

    @Autowired
    private FetchTxnApi fetchTxnApi;

    @Autowired
    private CancelTransactionApi cancelTransactionApi;

    @Autowired
    private WalletBalanceApi walletBalanceApi;


    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to add money in wallet", notes = "The API is used to add money to the wallet",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_ADDMONEY)

    public WalletApiResponse addMoney(@Valid @RequestBody AddMoneyInput
        addMoneyInput) {
        log.info("Inside walletController ,Start of addMoney");

        return ResponseUtils.successResponse(addMoneyInputApi.execute(addMoneyInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to credit/debit from wallet", notes = "The API is used to transfer money",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_PAYMENT)

    public WalletApiResponse payment(@Valid @RequestBody PaymentInput
        paymentInput) {
        log.info("Inside Pay , going to start Payment");

        return ResponseUtils.successResponse(paymentApi.execute(paymentInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to fetch Payee Name", notes = "The API is used to display name of Payee",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})
    @RequestMapping(method = RequestMethod.POST, value = C.API_FETCH_PAYEE_NAME)

    public WalletApiResponse fetchPayeeName(@Valid @RequestBody PaymentInput
        paymentInput) {
        log.info("Inside fetch , going to fetch payee name");

        return ResponseUtils.successResponse(payeeNameApi.execute(paymentInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to fetch Transactions", notes = "The API is used to fetch all Transactions of user",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})

    @RequestMapping(method = RequestMethod.POST, value = C.API_FETCH_TXNS)

    public WalletApiResponse fetchTransactions(@Valid @RequestBody FetchTxnInput
        fetchTxnInput) {
        log.info("Inside walletController ,Start of fetchTransactions");

        return ResponseUtils.successResponse(fetchTxnApi.execute(fetchTxnInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to cancel Transactions", notes = "The API is used to cancel ongoing transaction",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})

    @RequestMapping(method = RequestMethod.POST, value = C.API_CANCEL_TXNS)

    public WalletApiResponse cancelTransaction(@Valid @RequestBody CancelTransactionInput
        cancelTransactionInput) {
        log.info("Inside cancelTransaction ,going to cancel Transaction");

        return ResponseUtils.successResponse(cancelTransactionApi.execute(cancelTransactionInput));
    }

    @ApiOperation(httpMethod = "POST", consumes = "application/json", value =
        "Api to get balance", notes = "The API is used to getBalance in wallet",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful")})

    @RequestMapping(method = RequestMethod.POST, value = C.API_WALLET_BALANCE)

    public WalletApiResponse getBalance(@Valid @RequestBody WalletBalaceInput
        walletBalaceInput) {
        log.info("Inside getBalance ,going to get availabale balace in wallet");

        return ResponseUtils.successResponse(walletBalanceApi.execute(walletBalaceInput));
    }




}
