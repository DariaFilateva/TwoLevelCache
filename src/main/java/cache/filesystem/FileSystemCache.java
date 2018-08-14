package cache.filesystem;

import cache.AbstractCache;

import java.io.Serializable;

public class FileSystemCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V>{

    private static final String LEVEL = "secondLevelSize";
    private final String CACHE_DIR_PATH="";

    private int size;

    public FileSystemCache() throws Exception {
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
