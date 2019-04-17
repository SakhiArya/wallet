package com.agro.wallet.request;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WalletRegisterationInput extends ApiRequest {

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    private String lastName;

    @Email
    private String emailId;

    @NotNull
    private String displayName;

    @NotNull
    private Date dob;

    @NotNull
    @Valid
    private Address address;

    @Builder
    public WalletRegisterationInput(@NotNull String password,
        @NotNull String firstName, String lastName,
        @NotNull String addressLine, @NotNull String city,
        @NotNull String country, String pincode,
        @Email String emailId, @NotNull Date dob, @NotNull @Valid Address address, @NotNull String
        displayName, String mobileNumber) {
        super(mobileNumber);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.dob = dob;
        this.address = address;
        this.displayName = displayName;
    }
}
