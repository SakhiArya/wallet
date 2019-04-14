package com.agro.wallet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInput extends ApiRequest{

    private Double amount;
    private String payeeMobileNumber;
    private String note;

}
