package com.agro.wallet.service;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<T, ID extends Serializable> {

    JpaRepository<T, ID> getDao();


}