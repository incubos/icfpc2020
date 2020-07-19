package icfpc2020.eval;

import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public interface Universe {
    @NotNull
    LazyValue getValue(@NotNull String name);
}
