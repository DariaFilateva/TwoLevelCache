package cache.memory;

import cache.AbstractCache;
import cache.IChache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class MemoryCache extends AbstractCache {

    private static final String LEVEL = "sizeFirstLevel";

    private int size;

    public MemoryCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));

    }

    public void putToCache(Object key, Object object) {

    }

    public Object getFromCache(Object key) {
        return null;
    }

    public void removeObject(Object key) {

    }

    public void clearCache() {

    }
}
