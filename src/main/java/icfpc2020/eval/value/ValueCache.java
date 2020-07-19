package icfpc2020.eval.value;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ValueCache {
    private static final Map<LazyValue, LazyValue> valueCache = new HashMap<>();

    public static @Nullable LazyValue get(LazyValue lazyValue) {
        return valueCache.get(lazyValue);
    }

    public static void put(LazyValue key, LazyValue value) {
        valueCache.put(key, value);
    }
}
