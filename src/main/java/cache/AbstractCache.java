package cache;

import cache.algorithms.CacheAlogoritm;
import cache.algorithms.LRU;
import cache.algorithms.MFU;
import cache.memory.MemoryCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractCache<K, V> implements IChache<K, V> {

    public static final String STRATEGY = "strategy";

    private static final Logger logger = Logger.getLogger(MemoryCache.class.getName());
    private CacheAlogoritm cacheAlogoritm;

    protected HashMap<K, V> cache;
    protected int size;

    public AbstractCache() throws Exception {
        this.cacheAlogoritm = findStrategy();
    }

    public void putToCache(K key, V object) {
        if (cache.size() != size) {
            putToSpecificCache(key, object);
        } else {
            removeObject((K) cacheAlogoritm.getKeyToRemove());
            putToCache(key, object);
        }
    }

    public V getFromCache(K key) {
        return cache.get(key);
    }

    public void removeObject(K key) {
        cache.remove(key);
    }

    public void clearCache() {
        cache.clear();
    }

    protected Properties getProperties() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyPath);
        if (inputStream != null) {
            prop.load(inputStream);
            logger.info("Config file was reading succesfuly");
        } else {
            throw new FileNotFoundException("Property file: '" + propertyPath + "' not found in the classpath");
        }
        return prop;
    }

    protected String getSize(String level) throws IOException {
        return getProperties().getProperty(level);
    }

    protected abstract void putToSpecificCache(K key, V object);

    private CacheAlogoritm findStrategy() throws Exception {
        logger.info("Find strategy...");
        switch (getProperties().getProperty(STRATEGY)) {
            case "LRU":
                return new LRU();
            case "MFU":
                return new MFU();
         /*   case "LFU":
                return new CacheAlogoritm();
            case "FIFO":
                return new CacheAlogoritm();*/
        }
        throw new Exception("Strategy was not found");
    }
}
