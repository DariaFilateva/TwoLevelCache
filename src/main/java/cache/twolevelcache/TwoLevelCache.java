package cache.twolevelcache;

import cache.filesystem.FileSystemCache;
import cache.memory.MemoryCache;

import java.io.Serializable;

public class TwoLevelCache<K extends Serializable, V extends Serializable> {

    private MemoryCache<K, V> memoryCache = new MemoryCache<>();
    private FileSystemCache<K, V> fileSystemCache = new FileSystemCache<>();


    public TwoLevelCache() throws Exception {
    }


}
