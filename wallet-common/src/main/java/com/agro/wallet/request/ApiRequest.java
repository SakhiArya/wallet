package com.agro.wallet.request;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiRequest {

    private String token;

    @Size(min = 10, max = 10)
    private String mobileNumber;

    public ApiRequest(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public ApiRequest(String mobileNumber, String token) {
        this.token = token;
        this.mobileNumber = mobileNumber;
    }
}
