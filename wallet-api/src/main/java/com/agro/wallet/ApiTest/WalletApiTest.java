package com.agro.wallet.ApiTest;


import static org.junit.Assert.assertEquals;

import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.request.CancelTransactionInput;
import com.agro.wallet.request.CancelTransactionOutput;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.FetchUserOutput;
import com.agro.wallet.response.PaymentOutput;
import com.agro.wallet.response.WalletApiResponse;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

public class WalletApiTest extends WalletApiBaseTest {

    @Autowired
    private LoginStore loginStore;

    @Autowired
    private WalletEntityService walletEntityService;

    @Test
    public void addMoney() throws Exception {

        Double addBal = 100.00;

        //Add money for user One
        LoginData loginData = loginStore.getValue(tokenUserOne);
        WalletEntity walletEntity = walletEntityService.findById(loginData.getWalletId());

        Double bal = walletEntity.getBalance();

        AddMoneyInput addMoneyInput = new AddMoneyInput();
        addMoneyInput.setAmount(addBal);
        addMoneyInput.setMobileNumber(mobileUserOne);
        addMoneyInput.setToken(tokenUserOne);

        String inputJson = mapToJson(addMoneyInput);
        MvcResult mvcResult = getMvcResult(addMoneyUri,inputJson);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        walletEntity = walletEntityService.findById(loginData.getWalletId());
        Assert.assertEquals(bal+addBal,walletEntity.getBalance(),0);

        //Add money for user Two
        addBal = 200.00;
        loginData = loginStore.getValue(tokenUserTwo);
        walletEntity = walletEntityService.findById(loginData.getWalletId());

        bal = walletEntity.getBalance();

        addMoneyInput = new AddMoneyInput();
        addMoneyInput.setAmount(addBal);
        addMoneyInput.setMobileNumber(mobileUserTwo);
        addMoneyInput.setToken(tokenUserTwo);

        inputJson = mapToJson(addMoneyInput);
        mvcResult = getMvcResult(addMoneyUri,inputJson);
        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        walletEntity = walletEntityService.findById(loginData.getWalletId());
        Assert.assertEquals(bal+addBal,walletEntity.getBalance(),0);


    }
    @Test
    public void fetchPayeeNameAndPayment() throws Exception{
        Double bal = 56.00;
        String note = "Test Pay";
        String payeeMobNo = mobileUserOne;
        String payerMobNo = mobileUserTwo;
        String payerToken = tokenUserTwo;

        FetchUserOutput fetchUserOutput = fetchPayee(bal,note,payeeMobNo,payerToken,payerMobNo);
        Assert.assertNotNull(fetchUserOutput.getFirstName());
        Assert.assertNotNull(fetchUserOutput.getTxnId());

        String txnId = fetchUserOutput.getTxnId();
        PaymentInput paymentInput = getPaymentInput(bal,note,payeeMobNo,payerToken,payerMobNo);
        paymentInput.setTxnId(txnId);

        String inputJson = mapToJson(paymentInput);
        MvcResult mvcResult = getMvcResult(paymentUri,inputJson);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<PaymentOutput> walletApiResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<PaymentOutput>>(){});
        PaymentOutput paymentOutput = walletApiResponse.getResult();
        Assert.assertEquals(paymentOutput.getTransactionStatus(), TransactionStatus.SUCCESS);
    }
    @Test
    public void cancelTransaction() throws Exception{
        Double bal = 36.00;
        String note = "Test Cancel Pay";
        String payeeMobNo = mobileUserOne;
        String payerMobNo = mobileUserTwo;
        String payerToken = tokenUserTwo;

        FetchUserOutput fetchUserOutput = fetchPayee(bal,note,payeeMobNo,payerToken,payerMobNo);
        Assert.assertNotNull(fetchUserOutput.getFirstName());
        Assert.assertNotNull(fetchUserOutput.getTxnId());

        String txnId = fetchUserOutput.getTxnId();

        CancelTransactionInput cancelTransactionInput = new CancelTransactionInput();
        cancelTransactionInput.setTxnId(txnId);
        cancelTransactionInput.setMobileNumber(payerMobNo);
        cancelTransactionInput.setToken(payerToken);

        String inputJson = mapToJson(cancelTransactionInput);
        MvcResult mvcResult = getMvcResult(cancelTransactionsUri,inputJson);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<CancelTransactionOutput> walletApiResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<CancelTransactionOutput>>(){});

        CancelTransactionOutput cancelTransactionOutput = walletApiResponse.getResult();
        Assert.assertEquals(cancelTransactionOutput.getFinalTransactionStatus(), TransactionStatus.CANCELED);

        //Proceeding with Transaction after cancel -- should not complete
        PaymentInput paymentInput = getPaymentInput(bal,note,payeeMobNo,payerToken,payerMobNo);
        paymentInput.setTxnId(txnId);

        inputJson = mapToJson(paymentInput);
        mvcResult = getMvcResult(paymentUri,inputJson);

        status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<PaymentOutput> walletApiPayResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<PaymentOutput>>(){});
        Assert.assertEquals(walletApiPayResponse.getErrorCode(), ErrorCode.WALLET_STATUS);
        Assert.assertEquals(walletApiPayResponse.getErrorMessage(),ErrorCode.WALLET_STATUS.getDescription());
    }
    private PaymentInput getPaymentInput(Double bal,String note,String payeeMobNo,
        String payerToken,String payerMobNo){
        PaymentInput paymentInput = new PaymentInput();
        paymentInput.setAmount(bal);
        paymentInput.setNote(note);
        paymentInput.setPayeeMobileNumber(payeeMobNo);
        paymentInput.setToken(payerToken);
        paymentInput.setMobileNumber(payerMobNo);
        return paymentInput;
    }
    private FetchUserOutput fetchPayee(Double bal,String note,String payeeMobNo,
        String payerToken,String payerMobNo) throws Exception{
        String inputJson = mapToJson(getPaymentInput(bal,note,payeeMobNo,payerToken,payerMobNo));
        MvcResult mvcResult = getMvcResult(getPayeeNameUri,inputJson);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<FetchUserOutput> walletApiResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<FetchUserOutput>>(){});
        return  walletApiResponse.getResult();
    }
}