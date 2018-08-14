package cache.algorithms;

import java.util.Collections;
import java.util.Date;

public class LFU <K> extends CacheAlogoritm <K, Date> {

    @Override
    protected Date getKeyDataToRemove() {
        return Collections.min(this.useInfoMap.values());
    }

    @Override
    protected Date getDefaultKeyData() {
        return null;
    }
}
