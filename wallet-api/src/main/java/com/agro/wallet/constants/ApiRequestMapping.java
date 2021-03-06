package com.agro.wallet.constants;

public interface ApiRequestMapping {

    String API_REGISTERATION = "/register";

    String API_SUBMITOTP = "/submitOtp";

    String API_LOGIN = "/login";

    String API_LOGOUT = "/logout";

    String CONTROLLER_USER = "/user";

    String CONTROLLER_WALLET = "/wallet";

    String API_ADDMONEY = "/addMoney";

    String API_PAYMENT = "/payment";

    String API_FETCH_PAYEE_NAME = "/fetchPayeeName";

    String API_FETCH_TXNS = "/getTransactions";

    String API_CANCEL_TXNS = "/cancelTransactions";

    String API_WALLET_BALANCE = "/getBalance";
}
