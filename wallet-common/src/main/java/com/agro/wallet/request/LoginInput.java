package com.agro.wallet.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginInput extends ApiRequest {

    @NotEmpty
    private String mobileNumber;

    @NotEmpty
    private String password;

}
