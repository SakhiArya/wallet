package com.agro.wallet.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotNull
    private String addressLine;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String state;

    private String pincode;

}
