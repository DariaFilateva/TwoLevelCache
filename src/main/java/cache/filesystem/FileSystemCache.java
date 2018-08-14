package cache.filesystem;

import cache.AbstractCache;

import java.io.*;

public class FileSystemCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V> {

    private static final String LEVEL = "secondLevelSize";
    private final String CACHE_DIR_PATH = "";

    public FileSystemCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));
        initCachePath();
    }

   /* @Override
    public V getFromCache(K key) {

    }
*/
    @Override
    protected void putToSpecificCache(K key, V object) {
        try {
            FileOutputStream fileStream = new FileOutputStream(CACHE_DIR_PATH);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(object);
            objectStream.flush();
            objectStream.close();
            fileStream.flush();
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initCachePath(){
        File cachePath = new File(CACHE_DIR_PATH);
        if (!cachePath.exists()) {
            cachePath.mkdirs();
        }
    }

}

