package com.agro.wallet.repository;

import com.agro.wallet.entities.LoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends BaseRepository<LoginEntity, Integer> {


    LoginEntity findByMobileNumber(String mobileNumber);

    void deleteByMobileNumber(String mobileNumber);

}
