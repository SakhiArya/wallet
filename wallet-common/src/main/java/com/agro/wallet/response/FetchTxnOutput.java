package com.agro.wallet.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class FetchTxnOutput extends ApiResponse {

    List<Transactions> transactions;

}
