package cache;

import cache.algorithms.CacheAlogoritm;
import cache.algorithms.LRU;
import cache.memory.MemoryCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractCache<K, V> implements IChache<K, V> {

    public static final String STRATEGY = "strategy";

    private static Logger logger = Logger.getLogger(MemoryCache.class.getName());

    private CacheAlogoritm cacheAlogoritm;

    public AbstractCache() throws Exception {
        this.cacheAlogoritm = findStrategy();
    }

    public abstract void putToCache(K key, V object);

    public abstract Object getFromCache(K key);

    public abstract void removeObject(K key);

    public abstract void clearCache();

    protected Properties getProperties() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyPath);
        if (inputStream != null) {
            prop.load(inputStream);
            logger.info("Config file was reading succesfuly");
        } else {
            throw new FileNotFoundException("property file '" + propertyPath + "' not found in the classpath");
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
          /*  case "MRU":
                return new CacheAlogoritm();
            case "LFU":
                return new CacheAlogoritm();
            case "FIFO":
                return new CacheAlogoritm();*/
        }
        throw new Exception("Strategy was not found");
    }
}
