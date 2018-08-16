package cache.memory;

import cache.filesystem.FileSystemCache;
import org.junit.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MemoryCacheTest {


    @Test
    public void testConstructor() throws Exception {
        MemoryCache<String, String> memoryCache = new MemoryCache<>();
        Assert.assertNotNull(memoryCache);
    }

    @Test
    public void testPut() throws Exception {
        MemoryCache<String, String> memoryCache = new MemoryCache<>();
        memoryCache.put("key1", "value1");
        Assert.assertEquals(memoryCache.hasCapacity(), true);
        Assert.assertEquals(memoryCache.getFromCache("key1"), "value1");
    }
}