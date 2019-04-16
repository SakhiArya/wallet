package com.agro.wallet.service;

import com.agro.wallet.entities.LoginEntity;

public interface LoginEntityService extends BaseService<LoginEntity,Integer> {

    LoginEntity findByMobileNumber(String mobileNumber);

    void deleteByMobileNumber(String mobileNumber) ;

}
