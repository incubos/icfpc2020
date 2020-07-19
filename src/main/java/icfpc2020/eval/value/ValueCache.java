package icfpc2020.eval.value;

import java.util.HashMap;
import java.util.Map;

public class ValueCache {
    private static final Map<LazyValue, LazyValue> valueCache = new HashMap<>();

    public static LazyValue eval(final LazyValue lazyValue) {
        if (!valueCache.containsKey(lazyValue)) {
            valueCache.put(lazyValue, lazyValue.eval());
        }
        return valueCache.get(lazyValue);
    }
}
