package cache.memory;

import cache.AbstractCache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache<K, V> extends AbstractCache<K, V> {

    private static final String LEVEL = "firstLevelSize";

    public MemoryCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));
        cache = new HashMap<>(size);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        cacheAlogoritm.setKeyData(key, new Date());
    }

}

