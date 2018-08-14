package cache.memory;

import cache.AbstractCache;

import java.util.HashMap;

public class MemoryCache<K, V> extends AbstractCache<K, V> {

    private static final String LEVEL = "firstLevelSize";

    public MemoryCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));
        cache = new HashMap<>(size);
    }

    @Override
    protected void putToSpecificCache(K key, V object) {
        cache.put(key, object);
    }

}

