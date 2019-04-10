package com.agro.wallet.entities;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements
    Serializable {

}
