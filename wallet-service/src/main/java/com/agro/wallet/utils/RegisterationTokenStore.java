package com.agro.wallet.utils;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class RegisterationTokenStore implements ITokenStore<String, String> {

    private Cache authCache;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    private void init() {
        authCache = cacheManager.getCache("authCache");
    }

    @Override
    public String getValue(String key) {
        ValueWrapper value = authCache.get(key);
        return (value != null && value.get() != null) ? (String) value.get() : null;
    }

    @Override
    public void put(String key, String value) {
        authCache.put(key, value);

    }

    @Override
    public void remove(String key) {
        authCache.evict(key);
    }

    @Override
    public Set<String> getExistingKeys() {
        return new HashSet<String>(((Ehcache) authCache.getNativeCache()).getKeys());
    }

    @Override
    public boolean exists(String key) {
        ValueWrapper value = authCache.get(key);
        return (value != null && value.get() != null) ? true : false;
    }
}
