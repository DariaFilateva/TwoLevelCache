package cache.memory;

import cache.AbstractCache;

public class MemoryCache extends AbstractCache {

    private static final String LEVEL = "firstLevelSize";

    private int size;

    public MemoryCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));

    }

    @Override
    public void putToCache(Object key, Object object) {

    }

    @Override
    public Object getFromCache(Object key) {
        return null;
    }

    @Override
    public void removeObject(Object key) {

    }

    @Override
    public void clearCache() {

    }


}
