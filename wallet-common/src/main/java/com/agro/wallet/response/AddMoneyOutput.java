package com.agro.wallet.response;


import lombok.Builder;

@Builder
public class AddMoneyOutput extends ApiResponse {

    private String newBalance;

}
