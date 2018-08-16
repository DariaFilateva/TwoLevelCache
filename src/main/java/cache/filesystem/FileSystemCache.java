package cache.filesystem;

import cache.AbstractCache;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileSystemCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V> {

    private static final String LEVEL = "secondLevelSize";
    private final String CACHE_DIR_PATH = "/Applications/testTask/src/main/resources/filesystemcache/";
    private final Map<K, String> mapForPath;


    public FileSystemCache() throws Exception {
        super();
        clearCacheDirectiry();
        this.size = Integer.valueOf(getSize(LEVEL));
        initCachePath();
        mapForPath = new HashMap<>(size);
    }

    /*
    Если в кэше есть место, вставляем туда,
    если нет - согласно стратегии кеша вытесняем элемент,
     записываем его в файл
     */
    public void put(K key, V object) {
        if (hasCapacity()) {
            try {
                String fileName = "filesystemCache" + UUID.randomUUID() + ".txt";
                File file = new File(CACHE_DIR_PATH, fileName);
                mapForPath.put(key, fileName);
                cacheAlogoritm.setKeyData(key, new Date());
                serialize(file, object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            K keyToRemove = (K) cacheAlogoritm.getKeyToRemove();
            removeObject(keyToRemove);
            put(key, object);
            // removeFromCache(objectToRemoveFromMemory);

        }
    }

    public void removeObject(K keyToRemove){
        String pathToRemove = mapForPath.get(keyToRemove);
        removeFileWithObject(CACHE_DIR_PATH + pathToRemove);
        mapForPath.remove(keyToRemove);
        cacheAlogoritm.remove(keyToRemove);
    }

    public void removeFileWithObject(String pathToRemove) {
        File file = new File(pathToRemove);
        if (file.delete()) {
            System.out.println("файл был удален с корневой папки проекта");
        } else System.out.println("Файл не был найден в корневой папке проекта");
    }

    public boolean hasCapacity() {
        return mapForPath.size() < size;
    }

    public V getObject(K key) throws IOException, ClassNotFoundException {
        if (mapForPath.containsKey(key)) {

            try {
                FileInputStream fileStream = new FileInputStream(CACHE_DIR_PATH + mapForPath.get(key));
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                V deserializedObject = (V) objectStream.readObject();
                fileStream.close();
                objectStream.close();

                return deserializedObject;
            } catch (IOException ex) {
                return null;
            } catch (ClassNotFoundException ex) {
                return null;
            }
        }

        return null;
    }

    public void clearCacheDirectiry() {
        for (File myFile : new File(CACHE_DIR_PATH).listFiles()) {
            if (myFile.isFile()) myFile.delete();
        }
    }

    private void initCachePath() {
        File cachePath = new File(CACHE_DIR_PATH);
        if (!cachePath.exists()) {
            cachePath.mkdirs();
        }
    }

    private void serialize(File file, V object) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileStream = new FileOutputStream(file);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(object);
        objectStream.flush();
        objectStream.close();
        fileStream.flush();
        fileStream.close();
    }

    public int getSize() {
        return mapForPath.size();
    }
}

