package com.agro.wallet.impl;

import com.agro.wallet.PaymentService;
import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.PaymentOutput;
import com.agro.wallet.service.TransactionEntityService;
import com.agro.wallet.service.UserEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.CommonUtils;
import com.agro.wallet.utils.LoginData;
import com.agro.wallet.utils.LoginStore;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceImpl implements PaymentService {



    @Autowired
    TransactionEntityService transactionEntityService;

    @Autowired
    WalletEntityService walletEntityService;

    @Autowired
    CommonUtils commonUtils;

    @Override
    @Transactional
    public PaymentOutput payment(PaymentInput paymentInput, WalletEntity payeeWallet,WalletEntity payerWallet,TransactionEntity transaction) {

        transaction.setStatus(TransactionStatus.PENDING);
        transactionEntityService.getDao().save(transaction);
        Boolean isTransactionSuccessful = debitAndCredit(payerWallet,payeeWallet,paymentInput.getAmount());
        if (isTransactionSuccessful){
            transaction.setStatus(TransactionStatus.SUCESS);
            transactionEntityService.getDao().save(transaction);
        }else{
            transaction.setStatus(TransactionStatus.FAILED);
            transactionEntityService.getDao().save(transaction);
        }

        return PaymentOutput.builder().amount(paymentInput.getAmount()).transactionStatus(transaction.getStatus()).txnId(transaction.getTxnId()).build();
    }
    private Boolean isEnoughBalance(WalletEntity payer,Double payerAmount){
        if(payer.getBalance() >= payerAmount){
            return true;
        }
        if((payer.getBalance() - payerAmount) >= -50000){
            return true;
        }
        return false;
    }
    @Transactional
    private Boolean debitAndCredit(WalletEntity payerWallet,WalletEntity payeeWallet,Double amount){
        if(isEnoughBalance(payerWallet,amount)) {
            Double balance = payerWallet.getBalance();
            balance -= amount;
            payerWallet.setBalance(balance);

            Double balance1 = payeeWallet.getBalance();
            balance1 += amount;
            payeeWallet.setBalance(balance1);
            WalletEntity newPayeeWallet = walletEntityService.getDao().save(payeeWallet);
            WalletEntity newPayerWallet = walletEntityService.getDao().save(payerWallet);
            return verifyTransaction(payeeWallet,payerWallet,newPayeeWallet,newPayerWallet,amount);
        }
        return false;
    }
    public TransactionEntity createTransaction(PaymentInput paymentInput,WalletEntity payeeWallet,WalletEntity payerWallet){
           TransactionEntity transaction = new TransactionEntity();
           transaction.setTxnId(commonUtils.generateUUID("TXN"));
           transaction.setAmount(paymentInput.getAmount());
           transaction.setPayeeWalletId(payeeWallet.getWalletId());
           transaction.setPayerWalletId(payerWallet.getWalletId());
           transaction.setNote(paymentInput.getNote());
           transaction.setStatus(TransactionStatus.INITIATED);
           return transactionEntityService.getDao().save(transaction);
    }
    private Boolean verifyTransaction(WalletEntity payeeWallet,WalletEntity payerWallet,
        WalletEntity newPayeeWallet,WalletEntity newPayerWallet,Double amount){
        Double payeeBal = payeeWallet.getBalance()+amount;
        Double payerBal = payerWallet.getBalance()-amount;
        if (payeeBal.equals(newPayeeWallet.getBalance()) && payerBal.equals(newPayerWallet.getBalance())){
            return true;
        }
        return false;
    }
}
