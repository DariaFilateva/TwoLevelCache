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

    protected CacheAlogoritm cacheAlogoritm;
    protected HashMap<K, V> cache;
    protected int size;

    public AbstractCache() throws Exception {
        this.cacheAlogoritm = findStrategy();
    }

    public abstract void put(K key, V value);

    public V getFromCache(K key) {
        return cache.get(key);
    }

    public V getFromCacheToRemove() {
        return cache.get((K) cacheAlogoritm.getKeyToRemove());
    }

    public void removeObject(K key) {
        cache.remove(key);
    }

    public boolean hasCapacity() {
        return cache.size() < size;
    }

    public void removeObjectAccordingStrategy() {
        K key = getKeyAccordingStrategy();
        removeObject(key);
        cacheAlogoritm.remove(key);
    }

    public K getKeyAccordingStrategy() {
        return (K) cacheAlogoritm.getKeyToRemove();
    }

    public void clearCache() {
        cache.clear();
    }

    public int getSize() {
        return cache.size();
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
