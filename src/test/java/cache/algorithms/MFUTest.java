package cache.algorithms;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class MFUTest {
    private MFU<String> mfu;

    @BeforeClass
    public void init() {
        mfu = new MFU<>();
        mfu.setKeyData("key1", new Date());
        mfu.setKeyData("key2", new Date(1234));
    }

    @Test
    public void testGetKeyDataToRemove() throws Exception {
        Assert.assertEquals(mfu.getKeyDataToRemove(), mfu.getKeyData("key1"));
    }

    @Test
    public void testGetDefaultKeyData() throws Exception {
        Assert.assertNotNull(mfu.getDefaultKeyData());
    }
}
