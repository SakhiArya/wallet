package com.agro.wallet.cache;

import com.agro.wallet.utils.TokenUtils;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${auth.cache.size:50000}")
    private Integer authCacheSize;

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(authCache());
    }

    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager authCache() {
        CacheConfiguration authCacheConfig = getAuthCacheConfiguration("authCache", authCacheSize);
        CacheConfiguration userCacheConfig = getCacheConfiguration("userCache", authCacheSize);
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.name("authCache");
        config.addCache(authCacheConfig);
        config.addCache(userCacheConfig);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    private CacheConfiguration getCacheConfiguration(String cacheName, Integer cacheSize) {
        CacheConfiguration config = new CacheConfiguration();
        config.setName(cacheName);
        config.eternal(true);
        config.maxEntriesLocalHeap(cacheSize);

        PersistenceConfiguration persisConfig = new PersistenceConfiguration();
        persisConfig.strategy(Strategy.NONE);
        config.persistence(persisConfig);
        return config;
    }

    private CacheConfiguration getAuthCacheConfiguration(String cacheName, Integer cacheSize) {
        CacheConfiguration config = new CacheConfiguration();
        config.setName(cacheName);
        config.eternal(false);
        config.timeToLiveSeconds(TokenUtils.EXPIRATIONTIME);
        config.timeToIdleSeconds(0);
        config.maxEntriesLocalHeap(cacheSize);
        config.setCopyOnRead(true);

        PersistenceConfiguration persisConfig = new PersistenceConfiguration();
        persisConfig.strategy(Strategy.NONE);
        config.persistence(persisConfig);
        return config;
    }
}
