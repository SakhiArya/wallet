package com.agro.wallet.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TransactionStatus {

    INITIATED,
    PENDING,
    FAILED,
    SUCCESS,
    CANCELED;

    public static Set<TransactionStatus> terminalStatus = new HashSet<>(
        Arrays.asList(FAILED, SUCCESS, CANCELED));
}
