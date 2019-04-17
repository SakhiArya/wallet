package ApiTest;

import static org.junit.Assert.assertEquals;

import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.FetchUserOutput;
import com.agro.wallet.response.WalletApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.test.web.servlet.MvcResult;

public class CommonUtils extends WalletApiBaseTest {


    protected static FetchUserOutput fetchPayee(Double bal, String note, String payeeMobNo,
        String payerToken, String payerMobNo) throws Exception {
        String inputJson = mapToJson(
            getPaymentInput(bal, note, payeeMobNo, payerToken, payerMobNo));
        MvcResult mvcResult = getMvcResult(getPayeeNameUri, inputJson);

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        WalletApiResponse<FetchUserOutput> walletApiResponse =
            mapFromJson(content, new TypeReference<WalletApiResponse<FetchUserOutput>>() {
            });
        return walletApiResponse.getResult();
    }


    protected static PaymentInput getPaymentInput(Double bal, String note, String payeeMobNo,
        String payerToken, String payerMobNo) {
        PaymentInput paymentInput = new PaymentInput();
        paymentInput.setAmount(bal);
        paymentInput.setNote(note);
        paymentInput.setPayeeMobileNumber(payeeMobNo);
        paymentInput.setToken(payerToken);
        paymentInput.setMobileNumber(payerMobNo);
        return paymentInput;
    }

}
