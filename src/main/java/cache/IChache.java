package cache;

public interface IChache<K, V> {

    static final String propertyPath = "config.properties";

    public void put(K key, V objectValue);

    public Object getFromCache(K key);

    public void removeObject(K key);

    public void clearCache();

}
