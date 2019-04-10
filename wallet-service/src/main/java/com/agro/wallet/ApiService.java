package com.agro.wallet;

import com.agro.wallet.request.ApiRequest;
import com.agro.wallet.response.ApiResponse;
import com.agro.wallet.utils.ObjectUtils;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

public abstract class ApiService<I extends ApiRequest, O extends ApiResponse> {

    private static final Logger logger = LoggerFactory
        .getLogger(ApiService.class);


    public abstract O callApi(I input) throws WalletException;

    public O execute(I apiInput) throws WalletException {
        Date startTime = new Date();
        logApi(getApiName() + ": " + ObjectUtils.getStringJSONWithoutNullValues(apiInput), true);
        O apiOutput=null;
        try {
            apiOutput = callApi(apiInput);
        } finally {
            long totalTime = new Date().getTime() - startTime.getTime();
            logApi(getApiName() + ": " + logApiMessage(apiInput,apiOutput), false);
            logger.info("Total time= " + totalTime + "ms.");
        }

        return apiOutput;
    }

    protected String logApiMessage(I apiInput, O apiOutput){
        StringBuilder st=new StringBuilder();
        if(apiInput instanceof ApiRequest){
            st.append(ObjectUtils.getStringJSONWithoutNullValues((apiInput)));
        }
        if(apiOutput instanceof ApiResponse){
            st.append("|");
            st.append(ObjectUtils.getStringJSONWithoutNullValues((apiOutput)));
        }
        return st.toString();
    }



    protected void logApi(String logMessage,boolean isInput){
        StringBuilder sb = new StringBuilder("RULEAPI ");
        sb.append(isInput ? " API_IN:" : " API_OUT:");
        sb.append(logMessage);
        logger.info(sb.toString());
    }

    protected String getApiName() {
        Service apiService = AnnotationUtils.findAnnotation(this.getClass(), Service.class);
        return apiService.value();
    }

}
