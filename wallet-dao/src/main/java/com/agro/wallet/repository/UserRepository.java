package com.agro.wallet.repository;

import com.agro.wallet.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Integer> {

    UserEntity findByMobileNumber(String mobileNumber);

    UserEntity findByUserId(String userId);

    Integer deleteByMobileNumber(String mobileNumber);

}
