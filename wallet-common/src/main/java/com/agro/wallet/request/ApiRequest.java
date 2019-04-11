package com.agro.wallet.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRequest {

    private String token;

    private String mobileNumber;

}
