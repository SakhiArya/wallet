package com.agro.wallet.impl;

import com.agro.wallet.FetchTxnService;
import com.agro.wallet.PaymentService;
import com.agro.wallet.WalletException;
import com.agro.wallet.constants.ErrorCode;
import com.agro.wallet.constants.TransactionStatus;
import com.agro.wallet.entities.TransactionEntity;
import com.agro.wallet.entities.WalletEntity;
import com.agro.wallet.request.PaymentInput;
import com.agro.wallet.response.PaymentOutput;
import com.agro.wallet.service.TransactionEntityService;
import com.agro.wallet.service.WalletEntityService;
import com.agro.wallet.utils.CommonUtils;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    TransactionEntityService transactionEntityService;

    @Autowired
    WalletEntityService walletEntityService;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    FetchTxnService fetchTxnService;

    @Override
    public PaymentOutput payment(PaymentInput paymentInput, WalletEntity payeeWallet,WalletEntity payerWallet,String txnId) {

        updateTransactionStatus(txnId,TransactionStatus.PENDING);

        Boolean isTransactionSuccessful = debitAndCredit(payerWallet,payeeWallet,paymentInput.getAmount(),txnId);
        if (!isTransactionSuccessful) {
            updateTransactionStatus(txnId, TransactionStatus.FAILED);
        }
        TransactionEntity transaction = fetchTxnService.fetchOne(paymentInput.getTxnId());
        return PaymentOutput.builder().amount(paymentInput.getAmount()).
            transactionStatus(transaction.getStatus()).txnId(transaction.getTxnId()).build();
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
    private Boolean debitAndCredit(WalletEntity payerWallet,WalletEntity payeeWallet,Double amount,String txnId){
        if(isEnoughBalance(payerWallet,amount)) {
            Double balance = payerWallet.getBalance();
            balance -= amount;
            payerWallet.setBalance(balance);

            Double balance1 = payeeWallet.getBalance();
            balance1 += amount;
            payeeWallet.setBalance(balance1);
            WalletEntity newPayeeWallet = walletEntityService.getDao().save(payeeWallet);
            WalletEntity newPayerWallet = walletEntityService.getDao().save(payerWallet);
            Boolean flag = verifyTransaction(payeeWallet,payerWallet,newPayeeWallet,newPayerWallet);
            if(flag){
                updateTransactionStatus(txnId,TransactionStatus.SUCCESS);
            }
            return flag;
        }
        return false;
    }
    public TransactionEntity createTransaction(PaymentInput paymentInput,WalletEntity payeeWallet,WalletEntity payerWallet){
           TransactionEntity transaction = new TransactionEntity();
           transaction.setTxnId(commonUtils.generateUUID("TXN"));
           transaction.setAmount(paymentInput.getAmount());
           transaction.setPayeeWalletId(payeeWallet);
           transaction.setPayerWalletId(payerWallet);
           transaction.setNote(paymentInput.getNote());
           transaction.setStatus(TransactionStatus.INITIATED);
           return transactionEntityService.getDao().save(transaction);
    }
    private Boolean verifyTransaction(WalletEntity payeeWallet,WalletEntity payerWallet,
        WalletEntity newPayeeWallet,WalletEntity newPayerWallet){
        if(payeeWallet.getBalance().equals(newPayeeWallet.getBalance()) &&
            payerWallet.getBalance().equals(newPayerWallet.getBalance())){
            return true;
        }
        return false;
    }
    private Boolean updateTransactionStatus(TransactionEntity transaction,TransactionStatus
        transactionStatus) throws WalletException{
        if (!TransactionStatus.terminalStatus.contains(transaction.getStatus())){
            transaction.setStatus(transactionStatus);
            transactionEntityService.getDao().save(transaction);
            return true;
        }
        throw new WalletException(ErrorCode.WALLET_STATUS);
    }
    public Boolean updateTransactionStatus(String txnId,TransactionStatus transactionStatus)
        throws WalletException{
        TransactionEntity transaction = fetchTxnService.fetchOne(txnId);
        if (null!=transaction &&!TransactionStatus.terminalStatus.contains(transaction.getStatus
            ())){
            transaction.setStatus(transactionStatus);
            transactionEntityService.getDao().save(transaction);
            return true;
        }
        throw new WalletException(ErrorCode.WALLET_STATUS);
    }
}
