package cache.twolevelcache;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TwoLevelCacheTest {

    private TwoLevelCache<String, String> twoLevelCache;

    @BeforeClass
    public void init() throws Exception {
        twoLevelCache = new TwoLevelCache<>();
        twoLevelCache.put("key1", "value1");
        twoLevelCache.put("key2", "value2");
        twoLevelCache.put("key3", "value3");
        twoLevelCache.put("key4", "value4");
        twoLevelCache.put("key5", "value5");
    }

    @Test
    public void testPut() throws Exception {
        Assert.assertEquals(twoLevelCache.getFileSystemCache().getSize(), 2);
        Assert.assertEquals(twoLevelCache.getMemoryCache().getSize(), 2);
    }

    @Test
    public void testGetFromCache() throws Exception {
        Assert.assertEquals(twoLevelCache.getFromCache("key5"), "value5");
        Assert.assertEquals(twoLevelCache.getFromCache("key3"), "value3");
    }

    @Test
    public void testRemoveFromCache() throws Exception {
        twoLevelCache.removeObject("key4");
        twoLevelCache.removeObject("key3");
        Assert.assertEquals(twoLevelCache.getMemoryCache().getSize(), 1);
        Assert.assertEquals(twoLevelCache.getFileSystemCache().getSize(), 1);
    }

}