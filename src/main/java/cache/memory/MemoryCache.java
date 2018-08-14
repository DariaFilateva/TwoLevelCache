package cache.memory;

import cache.AbstractCache;

public class MemoryCache<K, V> extends AbstractCache<K, V> {

    private static final String LEVEL = "firstLevelSize";

    private int size;

    public MemoryCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));

    }

    @Override
    public void putToCache(K key, V object) {

    }

    @Override
    public Object getFromCache(K key) {
        return null;
    }

    @Override
    public void removeObject(K key) {

    }

    @Override
    public void clearCache() {

    }


}
