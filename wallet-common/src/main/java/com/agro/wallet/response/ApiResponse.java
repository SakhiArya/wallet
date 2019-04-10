package com.agro.wallet.response;


import com.agro.wallet.constants.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    @JsonInclude(Include.NON_NULL)
    private ErrorCode errorCode;

    @JsonInclude(Include.NON_NULL)
    private String errorMessage;

    public ApiResponse(){}

    public ApiResponse(ErrorCode errorCode,String errorMessage){
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }

}
