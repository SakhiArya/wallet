package com.agro.wallet.filter;


import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.request.ApiRequest;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component("AuthFilter")
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private LoginStore loginStore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        boolean authReqd = isAuthReqd(request);
        if (!authReqd) {
            filterChain.doFilter(request, response);
            return;
        }
        AuthenticationRequestWrapper requestAuth = new AuthenticationRequestWrapper(
            (HttpServletRequest) request);
        BufferedReader reader = requestAuth.getReader();
        Gson gson = new Gson();

        ApiRequest apiRequest = gson.fromJson(reader, ApiRequest.class);
        //ApiRequest apiRequest =(ApiRequest)request;
        LoginData loginData = new LoginData();
        if (null != apiRequest && null != apiRequest.getToken()) {
            loginData = loginStore.getValue(apiRequest.getToken());
        }
        if (StringUtils.isEmpty(loginData)) {
            throw new WalletException(ErrorCode.UNAUTH_USER);
        }

        if (null != apiRequest && null != apiRequest.getMobileNumber() && !apiRequest
            .getMobileNumber()
            .equals(loginData
                .getMobileNumber())) {
            throw new WalletException(ErrorCode.UNAUTH_USER);
        }

        filterChain.doFilter(requestAuth, response);

    }

    private boolean isAuthReqd(HttpServletRequest request) {
        String pathInfo = request.getServletPath();
        if ((pathInfo.startsWith("/user")
            || pathInfo.contains("swagger-resources")
            || pathInfo.endsWith(".html")
            || pathInfo.endsWith(".png")
            || pathInfo.endsWith(".js")
            || pathInfo.endsWith("/v2/api-docs")
            || pathInfo.contains("/webjars/")
            || pathInfo.endsWith(".css"))) {

            return false;
        }
        return true;
    }
}
