package cache.twolevelcache;

import cache.algorithms.LRU;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TwoLevelCacheTest {

    private TwoLevelCache<String, String> twoLevelCache;

    @BeforeClass
    public void init() throws Exception {
        twoLevelCache = new TwoLevelCache<>();
    }

    @Test
    public void testPutToCache() throws Exception {
        twoLevelCache.putToCache("key1", "value1");
        twoLevelCache.putToCache("key2", "value2");
        twoLevelCache.putToCache("key3", "value3");
    }

}