package cache;

public interface IChache {

    static final String propertyPath="config.properties";

    public void putToCache(Object key, Object object);

    public Object getFromCache(Object key);

    public void removeObject(Object key);

    public void clearCache();

}
