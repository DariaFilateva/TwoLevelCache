package cache.filesystem;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class FileSystemCacheTest {

    private final String CACHE_DIR_PATH = "/Applications/testTask/src/main/resources/filesystemcache/";

    @Test
    public void testPut() throws Exception {
        FileSystemCache<String, String> fileSystemCache = new FileSystemCache();
        fileSystemCache.put("key1", "value1");
    }

    @Test
    public void testGetObject() throws Exception {
        FileSystemCache<String, String> fileSystemCache = new FileSystemCache();
        fileSystemCache.put("key1", "value1");
        Assert.assertEquals(fileSystemCache.getObject("key1"), "value1");
    }

    @Test
    public void testRemoveFileWithObject() throws Exception {
        FileSystemCache<String, String> fileSystemCache = new FileSystemCache();
        fileSystemCache.put("key1", "value1");
        fileSystemCache.put("key2", "value2");
        fileSystemCache.put("key3", "value3");
        File listFile = new File(CACHE_DIR_PATH);
        File files[] = listFile.listFiles();
        Assert.assertEquals(files.length, 2);
    }
}