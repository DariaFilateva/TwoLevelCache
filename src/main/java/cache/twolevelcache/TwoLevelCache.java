package cache.twolevelcache;

import cache.filesystem.FileSystemCache;
import cache.memory.MemoryCache;

import java.io.Serializable;

/*
Двухуровневый кеш.
*       Объекты записываются в Кеше оперативной памяти (размерность которой задается в проперти файле),
        Если место в кеше оперативной памяти кончилось, достаем из кеша элемент (согласно заданному алгоритму) и записываем
        в файловый кеш
 */
public class TwoLevelCache<K extends Serializable, V extends Serializable> {

    private MemoryCache<K, V> memoryCache = new MemoryCache<>();
    private FileSystemCache<K, V> fileSystemCache = new FileSystemCache<>();


    public TwoLevelCache() throws Exception {
    }

    public void putToCache(K key, V object) {
        if (memoryCache.hasCapacity()) {
            memoryCache.put(key, object);
        } else {
            V objectToRemoveFromMemory = memoryCache.getFromCacheToRemove();
            fileSystemCache.put(key, objectToRemoveFromMemory);
            memoryCache.removeObjectAccordingStrategy();
            memoryCache.put(key, object);
        }
    }


}
