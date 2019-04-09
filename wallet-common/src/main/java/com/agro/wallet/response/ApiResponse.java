package com.agro.wallet.response;


import com.agro.wallet.constants.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse {

    private ErrorCode errorCode;

    private String errorMessage;

    public ApiResponse(){}

    public ApiResponse(ErrorCode errorCode,String errorMessage){
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }

}
