package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public interface LazyValue {
    @NotNull
    LazyValue apply(LazyValue... args);
}
