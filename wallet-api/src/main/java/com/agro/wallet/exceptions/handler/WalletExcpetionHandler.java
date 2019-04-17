package com.agro.wallet.exceptions.handler;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.request.ResponseUtils;
import com.agro.wallet.response.WalletApiResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(basePackages = {"com.agro.wallet"})
@RestController
@Slf4j
public class WalletExcpetionHandler {

    @ResponseBody
    @ExceptionHandler(WalletException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public WalletApiResponse illegalInputException(WalletException ex) {

        log.error("WalletException " + ex.getMessage());

        return ResponseUtils.failResponse(null != ex.getErrorCode()
                ? ex.getErrorCode()
                : ErrorCode.WALLET_EXCEPTION,

            null != ex.getErrorMessage()
                ? ex.getErrorMessage()
                : ErrorCode.WALLET_EXCEPTION.getDescription());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public WalletApiResponse handleInvalidInputException(HttpServletRequest request,
        Exception e) {
        log.error("handleInvalidInputException " + e.getMessage());
        return ResponseUtils
            .failResponse(ErrorCode.INVALID_INPUT, null != e.getCause().getMessage()
                ? e.getCause().getMessage() : "no cause found");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = NOT_ACCEPTABLE)
    @ResponseBody
    public WalletApiResponse handleDataIntegrityException(HttpServletRequest request,
        Exception e) {
        log.error("handleDataIntegrityException " + e.getMessage());
        return ResponseUtils.failResponse(ErrorCode.SQL_EXCEPTION,
            ErrorCode.SQL_EXCEPTION.getDescription() + " " + null != e.getCause().getMessage()
                ? e.getCause().getCause().getMessage() : "no cause found");
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WalletApiResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException " + e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError fieldError = result.getFieldErrors().get(0);
        return ResponseUtils.failResponse(ErrorCode.INVALID_INPUT,
            fieldError.getField() + " " + fieldError.getDefaultMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WalletApiResponse handleException(HttpServletRequest request,
        Exception e) {
        log.error("Exception occured ", e);
        return ResponseUtils.failResponse(ErrorCode.WALLET_EXCEPTION,
            "Internal Exception occured");
    }

}
