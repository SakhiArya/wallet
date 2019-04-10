package com.agro.wallet.service;

import com.agro.wallet.entities.LoginEntity;
import com.agro.wallet.entities.UserEntity;

public interface UserEntityService extends BaseService<UserEntity,Integer> {

    UserEntity findIfUserAlreadyExists(String mobileNumber);

}
