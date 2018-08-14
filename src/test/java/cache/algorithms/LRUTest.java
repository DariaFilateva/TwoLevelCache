package cache.algorithms;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;


public class LRUTest {

    private LRU<String> lru;

    @BeforeClass
    public void init() {
        lru = new LRU<>();
        lru.setKeyData("key1", new Date());
        lru.setKeyData("key2", new Date(1234));
    }


    @Test
    public void testGetKeyDataToRemove() throws Exception {
        Assert.assertEquals(lru.getKeyDataToRemove(), lru.getKeyData("key2"));
    }

    @Test
    public void testGetDefaultKeyData() throws Exception {
        Assert.assertNotNull(lru.getDefaultKeyData());
    }
}
