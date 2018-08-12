package cache;

public interface IChache {



    public void putToCache(Object key, Object object);

    public Object getFromCache(Object key);

    public void removeObject(Object key);

    public void clearCache();

}
