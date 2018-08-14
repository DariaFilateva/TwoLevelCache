package cache.algorithms;

import java.util.HashMap;
import java.util.Map;

public abstract class CacheAlogoritm<K, KeyDate> {

    protected Map<K, KeyDate> useInfoMap = new HashMap();

    public K getKeyToRemove() {
        KeyDate keyDataToRemove = getKeyDataToRemove();

        for (Map.Entry e : this.useInfoMap.entrySet()) {
            if (((KeyDate) e.getValue()).equals(keyDataToRemove)) {
                return (K) e.getKey();
            }
        }
        return null;
    }

    protected abstract KeyDate getKeyDataToRemove();

    public void setKeyData(K key) {
        setKeyData(key, getDefaultKeyData());
    }

    protected abstract KeyDate getDefaultKeyData();

    public void setKeyData(K key, KeyDate data) {
        useInfoMap.put(key, data);
    }

    public void remove(K key) {
        useInfoMap.remove(key);
    }

    public KeyDate getKeyData(K key) {
        return useInfoMap.get(key);
    }
}
