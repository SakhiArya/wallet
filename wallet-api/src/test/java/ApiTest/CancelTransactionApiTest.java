package ApiTest;


import static ApiTest.CommonUtils.fetchPayee;
import static ApiTest.CommonUtils.getPaymentInput;
import static org.junit.Assert.assertEquals;

import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.request.CancelTransactionInput;
import com.agro.wallet.request.CancelTransactionOutput;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.FetchUserOutput;
import com.agro.wallet.response.PaymentOutput;
import com.agro.wallet.response.WalletApiResponse;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginStore;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CancelTransactionApiTest extends WalletApiBaseTest {

    @Autowired
    private LoginStore loginStore;

    @Autowired
    private WalletEntityService walletEntityService;

    @Test
    public void cancelTransaction() throws Exception {
        Double bal = 36.00;
        String note = "Test Cancel Pay";
        String payeeMobNo = mobileUserOne;
        String payerMobNo = mobileUserTwo;
        String payerToken = tokenUserTwo;

        FetchUserOutput fetchUserOutput = fetchPayee(bal, note, payeeMobNo, payerToken, payerMobNo);
        Assert.assertNotNull(fetchUserOutput.getFirstName());
        Assert.assertNotNull(fetchUserOutput.getTxnId());

        String txnId = fetchUserOutput.getTxnId();

        CancelTransactionInput cancelTransactionInput = new CancelTransactionInput();
        cancelTransactionInput.setTxnId(txnId);
        cancelTransactionInput.setMobileNumber(payerMobNo);
        cancelTransactionInput.setToken(payerToken);

        String inputJson = mapToJson(cancelTransactionInput);
        MvcResult mvcResult = getMvcResult(cancelTransactionsUri, inputJson);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<CancelTransactionOutput> walletApiResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<CancelTransactionOutput>>() {
            });

        CancelTransactionOutput cancelTransactionOutput = walletApiResponse.getResult();
        Assert.assertEquals(cancelTransactionOutput.getFinalTransactionStatus(),
            TransactionStatus.CANCELED);

        //Proceeding with Transaction after cancel -- should not complete
        PaymentInput paymentInput = getPaymentInput(bal, note, payeeMobNo, payerToken, payerMobNo);
        paymentInput.setTxnId(txnId);

        inputJson = mapToJson(paymentInput);
        mvcResult = getMvcResult(paymentUri, inputJson);

        status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<PaymentOutput> walletApiPayResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<PaymentOutput>>() {
            });
        Assert.assertEquals(walletApiPayResponse.getErrorCode(), ErrorCode.WALLET_STATUS);
        Assert.assertEquals(walletApiPayResponse.getErrorMessage(),
            ErrorCode.WALLET_STATUS.getDescription());
    }

}