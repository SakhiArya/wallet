package com.agro.wallet.service.impl;

import com.agro.wallet.entities.AddressEntity;
import com.agro.wallet.repository.AddressRepository;
import com.agro.wallet.service.AddressEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends
    BaseServiceImpl<AddressEntity, Integer> implements
    AddressEntityService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressRepository getDao() {
        return addressRepository;
    }

    @Override
    public AddressEntity deleteByAddressId(String addressId) {
        return getDao().deleteByAddressId(addressId);
    }
}
