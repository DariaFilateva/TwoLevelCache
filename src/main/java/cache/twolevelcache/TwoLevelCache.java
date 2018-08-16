package cache.twolevelcache;

import cache.IChache;
import cache.filesystem.FileSystemCache;
import cache.memory.MemoryCache;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/*
* Двухуровневый кеш.
* Объекты записываются в Кеше оперативной памяти
* (размерность которой задается в проперти файле),
* достаем из кеша элемент (согласно заданному алгоритму) и записываем
* в файловый кеш
*/
public class TwoLevelCache<K extends Serializable, V extends Serializable> implements IChache<K, V> {

    private MemoryCache<K, V> memoryCache = new MemoryCache<>();
    private FileSystemCache<K, V> fileSystemCache = new FileSystemCache<>();


    public TwoLevelCache() throws Exception {
    }

    public MemoryCache<K, V> getMemoryCache() {
        return memoryCache;
    }

    public FileSystemCache<K, V> getFileSystemCache() {
        return fileSystemCache;
    }

    @Override
    public void put(K key, V object) {
        if (memoryCache.hasCapacity()) {
            memoryCache.put(key, object);
        } else {
            V objectToRemoveFromMemory = memoryCache.getFromCacheToRemove();
            fileSystemCache.put(memoryCache.getKeyAccordingStrategy(), objectToRemoveFromMemory);
            memoryCache.removeObjectAccordingStrategy();
            memoryCache.put(key, object);
        }
    }

    public V getFromCache(K key) throws IOException, ClassNotFoundException {
        if (memoryCache.getFromCache(key) == null) {
            return fileSystemCache.getObject(key);
        } else return memoryCache.getFromCache(key);
    }

    @Override
    public void removeObject(K key) {
        if (memoryCache.getFromCache(key) != null) {
            memoryCache.removeObject(key);
        } else {
            fileSystemCache.removeObject(key);
        }
    }

    @Override
    public void clearCache() {
        memoryCache.clearCache();
        fileSystemCache.clearCacheDirectiry();
    }
}

