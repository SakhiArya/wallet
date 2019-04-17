package com.agro.wallet.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    private Double amount;
    private String status;
    private String note;
    private Date paidOn;
    private String paidTo;

}
