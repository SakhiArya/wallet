package com.agro.wallet.request;

import java.time.LocalDate;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRegisterationInput extends ApiRequest {

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    private String lastName;

    @Size(min = 10,max = 10)
    private String mobileNumber;

    @NotNull
    private String addressLine;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String state;

    private String pincode;

    @Email
    private String emailId;

    @NotNull
    private Date dob;

    @Builder
    public WalletRegisterationInput(@NotNull String password,
        @NotNull String firstName, String lastName,
        @Size(min = 10, max = 10) String mobileNumber,
        @NotNull String addressLine, @NotNull String city,
        @NotNull String country, String pincode,
        @Email String emailId, @NotNull Date dob) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
        this.emailId = emailId;
        this.dob = dob;
    }
}
