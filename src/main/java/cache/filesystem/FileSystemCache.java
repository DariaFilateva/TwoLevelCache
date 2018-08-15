package cache.filesystem;

import cache.AbstractCache;

import java.io.*;
import java.util.HashMap;

public class FileSystemCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V> {

    private static final String LEVEL = "secondLevelSize";
    private final String CACHE_DIR_PATH = "/Applications/testTask/src/main/resources/filesystemCache.txt";
    private static int capacity;


    public FileSystemCache() throws Exception {
        super();
        this.size = Integer.valueOf(getSize(LEVEL));
        initCachePath();
        cache = new HashMap<>(size);
        capacity = 0;
    }

    /*
    Если в кэше есть место, вставляем туда, если нет - согласно стратегии кеша вытесняем элемент, записываем его в файл
     */
    public void put(K key, V object) {
        if (hasCapacity()) {
            try {
                cache.put(key, object);
                serialize(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            V objectToRemoveFromMemory = this.getFromCacheToRemove();
            cache.remove(objectToRemoveFromMemory);

        }
    }

    @Override
    protected void putToSpecificCache(K key, V object) {

    }

    private void initCachePath() {
        File cachePath = new File(CACHE_DIR_PATH);
        if (!cachePath.exists()) {
            cachePath.mkdirs();
        }
    }

    private void serialize(V object) throws IOException {
        FileOutputStream fileStream = new FileOutputStream(CACHE_DIR_PATH);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(object);
        objectStream.flush();
        objectStream.close();
        fileStream.flush();
        fileStream.close();
        capacity++;
    }
}

