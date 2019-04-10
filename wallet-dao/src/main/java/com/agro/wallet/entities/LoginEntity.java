package com.agro.wallet.entities;

import com.agro.wallet.utils.AESEncrytionUtils;
import com.agro.wallet.utils.Encrypted;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Builder;

@Entity
@Table(name="login",indexes = {
    @Index(name="mobile_number_UNIQUE",columnList= "mobile_number",unique=true),
    @Index(name="user_id_UNIQUE",columnList= "user_id",unique=true),
    @Index(name="login_user_fk_idx",columnList= "user_id",unique=true)
})
@Builder
public class LoginEntity extends AuditedEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="mobile_number",nullable = false)
    private String mobileNumber;

    @Column(name="user_id",nullable = false)
    private String userId;

    @Column(name="password",nullable = false)
    @Convert(converter = AESEncrytionUtils.class)
    @Encrypted
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
