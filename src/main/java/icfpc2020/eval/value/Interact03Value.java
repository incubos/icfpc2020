package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Interact03Value implements LazyValue {
    public static final LazyValue INSTANCE = new Interact03Value();

    private Interact03Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue protocol) {
        return new Interact02Value(protocol);
    }

    @Override
    public String toString() {
        return "interact";
    }
}
