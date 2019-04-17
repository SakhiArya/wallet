package com.agro.wallet.service;

import com.agro.wallet.entities.AddressEntity;

public interface AddressEntityService extends BaseService<AddressEntity, Integer> {

    AddressEntity deleteByAddressId(String addressId);

}
