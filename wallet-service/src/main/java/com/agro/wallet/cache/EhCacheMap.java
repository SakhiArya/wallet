package com.agro.wallet.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.util.StringUtils;

public class EhCacheMap<K, V> implements Map<K, V> {

    private final Ehcache ehcache;

    public EhCacheMap(String cacheName, CacheManager cacheManager) {
        if (!StringUtils.hasText(cacheName)) {
            throw new IllegalArgumentException("Cache name can't be empty!!!");
        }
        ehcache = cacheManager.getCache(cacheName);

        if (null == ehcache) {
            throw new CacheException("Unable to create a cache!!!");
        }
    }

    @Override
    public void clear() {
        ehcache.removeAll();
    }

    @Override
    public boolean containsKey(Object key) {
        return ehcache.get(key) == null;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(ehcache.getKeys());
    }

    @Override
    public V put(K key, V value) {
        ehcache.put(new Element(key, value));
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            ehcache.put(new Element(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public V remove(Object key) {
        final Element element = ehcache.get(key);
        if (null != element) {
            ehcache.remove(key);
            return (V) element.getObjectValue();
        }
        return null;
    }

    @Override
    public int size() {
        return ehcache.getSize();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }
}
