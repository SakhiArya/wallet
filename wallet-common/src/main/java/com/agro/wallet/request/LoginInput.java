package com.agro.wallet.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginInput extends ApiRequest {

    private String mobileNumber;

    private String password;

}
