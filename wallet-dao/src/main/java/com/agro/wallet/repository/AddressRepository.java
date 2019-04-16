package com.agro.wallet.repository;

import com.agro.wallet.entities.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<AddressEntity, Integer>{

    AddressEntity deleteByAddressId(String addressId);

}
