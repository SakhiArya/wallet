package com.agro.wallet.utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OtpUtil {

    static String url = "http://www.way2sms.com";

    public String sendTxtMessageForOTP(String apiKey, String secretKey, String useType,
        String phone, String message, String senderId) {
        StringBuilder content = new StringBuilder();
        log.info("inside sendTxtMessageForOTP");
        try {
            // construct data
            JSONObject urlParameters = new JSONObject();
            urlParameters.put("apikey", apiKey);
            urlParameters.put("secret", secretKey);
            urlParameters.put("usetype", useType);
            urlParameters.put("phone", phone);
            urlParameters.put("message", URLEncoder.encode(message, "UTF-8"));
            urlParameters.put("senderid", senderId);
            URL obj = new URL(url + "/api/v1/sendTxtMessageForOTP");
            // send data
            HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(urlParameters.toString().getBytes());
            // get the response
            BufferedReader bufferedReader = null;
            if (httpConnection.getResponseCode() == 200) {
                log.info("receieved 200 from ways 2 sms");
                bufferedReader = new BufferedReader(
                    new InputStreamReader(httpConnection.getInputStream()));
            } else {
                log.info("receieved failure from ways 2 sms");
                bufferedReader = new BufferedReader(
                    new InputStreamReader(httpConnection.getErrorStream()));
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {

            log.error("WalletException at{}:", ex);
        }
        return content.toString();
    }


}

