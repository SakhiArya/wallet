package com.agro.wallet.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInput extends ApiRequest{

    @NotNull
    private Double amount;

    @Size(min = 10, max = 10)
    private String payeeMobileNumber;
    private String note;

    @NotNull
    private String txnId;

}
