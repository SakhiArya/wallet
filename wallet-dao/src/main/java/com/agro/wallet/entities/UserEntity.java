package com.agro.wallet.entities;


import com.agro.wallet.constants.UserStatus;
import com.agro.wallet.constants.UserType;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity extends AuditedEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id",nullable = false)
    private String userId;

    @OneToOne(targetEntity=WalletEntity.class,cascade= CascadeType.ALL)
    @JoinColumn(name="wallet_id",referencedColumnName="wallet_id")
    private String walletId;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mobile_number",nullable = false)
    private String mobileNumber;

    @Column(name="address_id",nullable = false)
    private String addressId;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="dob",nullable = false)
    private Date dob;

    @Enumerated(EnumType.STRING)
    @Column(name="user_type",nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name="user_status",nullable = false)
    private UserStatus userStatus;

/*    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }*/
}
