package ApiTest;

import static org.junit.Assert.assertEquals;

import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.AddMoneyInput;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

public class AddMoneyApiTest extends WalletApiBaseTest {


    @Autowired
    private WalletEntityService walletEntityService;
    @Autowired
    private LoginStore loginStore;


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
        MvcResult mvcResult = getMvcResult(addMoneyUri, inputJson);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        walletEntity = walletEntityService.findById(loginData.getWalletId());
        Assert.assertEquals(bal + addBal, walletEntity.getBalance(), 0);

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
        mvcResult = getMvcResult(addMoneyUri, inputJson);
        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        walletEntity = walletEntityService.findById(loginData.getWalletId());
        Assert.assertEquals(bal + addBal, walletEntity.getBalance(), 0);

    }
}
