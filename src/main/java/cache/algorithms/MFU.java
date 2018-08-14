package cache.algorithms;

import java.util.Collections;
import java.util.Date;

/**
 * Класс стратегии кеширования "Наиболее недавно использовавшийся".
 * @autor DariaFilateva
 */
public class MFU <K> extends CacheAlogoritm <K, Date> {

    @Override
    protected Date getKeyDataToRemove() {
        return Collections.max(this.useInfoMap.values());
    }

    @Override
    protected Date getDefaultKeyData() {
        return new Date();
    }
}
