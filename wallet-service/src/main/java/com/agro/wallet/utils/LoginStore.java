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
public class LoginStore implements ITokenStore<String, LoginData>  {


    private Cache userCache;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    private void init() {
        userCache = cacheManager.getCache("userCache");
    }

    @Override
    public LoginData getValue(String key) {
        ValueWrapper value = userCache.get(key);
        return (value != null && value.get() != null) ? (LoginData) value.get() : null;
    }

    @Override
    public void put(String key, LoginData value) {
        userCache.put(key, value);
    }

    @Override
    public void remove(String key) {
        userCache.evict(key);
    }

    @Override
    public Set<String> getExistingKeys() {
        return new HashSet<String>(((Ehcache) userCache.getNativeCache()).getKeys());
    }

    @Override
    public boolean exists(String key) {
        ValueWrapper value = userCache.get(key);
        return (value != null && value.get() != null) ? true : false;
    }


}
