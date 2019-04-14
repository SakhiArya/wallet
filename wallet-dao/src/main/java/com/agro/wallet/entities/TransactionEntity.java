package com.agro.wallet.entities;

import com.agro.wallet.constants.TransactionStatus;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="transactions",indexes = {@Index(name = "txn_id_index",  columnList="txn_id", unique = true)})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TransactionEntity extends AuditedEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="txn_id",nullable = false)
    private String txnId;

    @OneToOne(targetEntity=WalletEntity.class,cascade= CascadeType.ALL)
    @JoinColumn(name="payer_wallet_id",referencedColumnName="wallet_id",nullable = false)
    private String payerWalletId;

    @OneToOne(targetEntity=WalletEntity.class,cascade= CascadeType.ALL)
    @JoinColumn(name="payee_wallet_id",referencedColumnName="wallet_id",nullable = false)
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

}
