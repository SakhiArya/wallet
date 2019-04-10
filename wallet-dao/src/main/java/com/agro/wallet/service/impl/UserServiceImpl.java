package com.agro.wallet.service.impl;

import com.agro.wallet.entities.UserEntity;
import com.agro.wallet.repository.UserRepository;
import com.agro.wallet.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends
    BaseServiceImpl<UserEntity, Integer> implements
    UserEntityService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserRepository getDao() {
        return userRepository;
    }

    @Override
    public UserEntity findIfUserAlreadyExists(String mobileNumber) {
        return getDao().findByMobileNumber(mobileNumber);
    }

    @Override
    public UserEntity findByUserId(String userId) {
        return getDao().findByUserId(userId);
    }
}
