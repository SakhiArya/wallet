package com.agro.wallet.filter;


import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.request.ApiRequest;
import com.agro.wallet.utils.LoginStore;
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
        boolean authReqd=isAuthReqd(request);
        if(!authReqd){
            filterChain.doFilter(request, response);
            return;
        }

        ApiRequest apiRequest =(ApiRequest)request;
        if(StringUtils.isEmpty(loginStore.getValue(apiRequest.getToken()))){
            throw new WalletException(ErrorCode.UNAUTH_USER);
        }

    }

    private boolean isAuthReqd(HttpServletRequest request) {
        String pathInfo = request.getServletPath();
        if((pathInfo.startsWith("/user")
            ||  pathInfo.contains("swagger-resources")
            ||pathInfo.endsWith(".html")
            || pathInfo.endsWith(".png")
            || pathInfo.endsWith(".js")
            || pathInfo.endsWith("/v2/api-docs")
            || pathInfo.contains("/webjars/")
            || pathInfo.endsWith(".css"))){

            return false;
        }
        return true;
    }
}
