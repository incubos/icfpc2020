package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Interact02Value implements LazyValue {
    @NotNull
    private final LazyValue protocol;

    public Interact02Value(@NotNull final LazyValue protocol) {
        this.protocol = protocol;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue state) {
        return new Interact01Value(protocol, state);
    }

    @Override
    public String toString() {
        return "(interact " + protocol + ")";
    }
}
