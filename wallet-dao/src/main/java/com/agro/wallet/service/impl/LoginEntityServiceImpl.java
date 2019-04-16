package com.agro.wallet.service.impl;

import com.agro.wallet.entities.LoginEntity;
import com.agro.wallet.repository.LoginRepository;
import com.agro.wallet.service.LoginEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginEntityServiceImpl extends
    BaseServiceImpl<LoginEntity, Integer> implements
    LoginEntityService {


    @Autowired
    private LoginRepository loginRepository;

    @Override
    public LoginRepository getDao() {
        return loginRepository;
    }

    @Override
    public LoginEntity findByMobileNumber(String mobileNumber) {
        return getDao().findByMobileNumber(mobileNumber);
    }

    @Override
    public void deleteByMobileNumber(String mobileNumber) {
        getDao().deleteByMobileNumber(mobileNumber);
    }
}
