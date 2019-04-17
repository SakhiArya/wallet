package com.agro.wallet.util;

public class Queries {

    public static final String getTransactions =
        "select txn.amount as amount , txn.status as status,txn.note as note,txn.create_ts as "
            + "paidOn ,user.first_name as paidTo from transactions as txn join user as user on "
            + "txn.payee_wallet_id =user.wallet_id where txn. payer_wallet_id =:walletId";


    public static final String getReceivedTransactions =
        "select txn.amount as amount , txn.status as status,txn.note as note,txn.create_ts as "
            + "receivedOn ,user.first_name as receivedFrom from transactions as txn join user as user "
            + "on "
            + "txn.payee_wallet_id =user.wallet_id where txn. payee_wallet_id =:walletId";


}
