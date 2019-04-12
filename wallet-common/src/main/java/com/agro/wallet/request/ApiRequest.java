package com.agro.wallet.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRequest {

    private String token;

    @Size(min = 10,max = 10)
    private String mobileNumber;

}
