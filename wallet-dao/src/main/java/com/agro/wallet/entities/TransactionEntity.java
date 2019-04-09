package com.agro.wallet.entities;

import com.agro.wallet.constants.TransactionStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Table(name="transactions")
public class TransactionEntity extends AuditedEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="payer_wallet_id",nullable = false)
    private String payerWalletId;

    @Column(name="payee_wallet_id",nullable = false)
    private String payeeWalletId;

    @Column(name="amount",nullable = false)
    private Double amount;

    @Column(name="note")
    private String  note;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private TransactionStatus status;

    @Version
    @Column(name = "lock_id", columnDefinition = "int(5) default 0", nullable = false)
    private Integer  lockId = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayerWalletId() {
        return payerWalletId;
    }

    public void setPayerWalletId(String payerWalletId) {
        this.payerWalletId = payerWalletId;
    }

    public String getPayeeWalletId() {
        return payeeWalletId;
    }

    public void setPayeeWalletId(String payeeWalletId) {
        this.payeeWalletId = payeeWalletId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Integer getLockId() {
        return lockId;
    }

    public void setLockId(Integer lockId) {
        this.lockId = lockId;
    }
}
