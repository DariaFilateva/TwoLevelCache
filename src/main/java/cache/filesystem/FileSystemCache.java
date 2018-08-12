package cache.filesystem;

import cache.AbstractCache;

public class FileSystemCache extends AbstractCache{

    private static final String LEVEL = "secondLevelSize";

    private int size;

    public FileSystemCache() throws Exception {
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
