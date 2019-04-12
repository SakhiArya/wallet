package com.agro.wallet.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddMoneyOutput extends ApiResponse {

    private String newBalance;

}
