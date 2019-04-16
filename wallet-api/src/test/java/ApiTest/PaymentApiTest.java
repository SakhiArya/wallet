package ApiTest;

import static org.junit.Assert.assertEquals;

import com.agro.wallet.constants.TransactionStatus;
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
public class PaymentApiTest extends WalletApiBaseTest {


    @Autowired
    private WalletEntityService walletEntityService;
    @Autowired
    private LoginStore loginStore;

    @Test
    public void fetchPayeeNameAndPayment() throws Exception{
        Double bal = 56.00;
        String note = "Test Pay";
        String payeeMobNo = mobileUserOne;
        String payerMobNo = mobileUserTwo;
        String payerToken = tokenUserTwo;

        FetchUserOutput fetchUserOutput = CommonUtils.fetchPayee(bal,note,payeeMobNo,payerToken,
            payerMobNo);
        Assert.assertNotNull(fetchUserOutput.getFirstName());
        Assert.assertNotNull(fetchUserOutput.getTxnId());

        String txnId = fetchUserOutput.getTxnId();
        PaymentInput paymentInput = CommonUtils.getPaymentInput(bal,note,payeeMobNo,payerToken,
            payerMobNo);
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

}
