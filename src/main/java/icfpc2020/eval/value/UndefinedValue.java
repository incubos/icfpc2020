package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class UndefinedValue implements LazyValue {
    @NotNull
    private final String name;

    public UndefinedValue(@NotNull final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "undefined " + name;
    }


    @Override
    @NotNull
    public LazyValue apply(@NotNull LazyValue arg) {
        throw new UnsupportedOperationException(toString());
    }

    @Override
    public LazyValue eval() {
        throw new UnsupportedOperationException(toString());
    }
}
