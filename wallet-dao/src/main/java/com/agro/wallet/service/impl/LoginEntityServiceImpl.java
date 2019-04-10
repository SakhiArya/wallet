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
    private LoginRepository conditionRepository;

    @Override
    public LoginRepository getDao() {
        return conditionRepository;
    }

    @Override
    public LoginEntity findByMobileNumber(String mobileNumber) {
        return getDao().findByMobileNumber(mobileNumber);
    }
}
