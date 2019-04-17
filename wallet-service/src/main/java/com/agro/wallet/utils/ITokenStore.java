package com.agro.wallet.utils;

import java.util.Set;

public interface ITokenStore<K, V> {

    V getValue(K key);

    void put(K key, V value);

    void remove(K key);

    public Set<K> getExistingKeys();

    boolean exists(K key);
}
