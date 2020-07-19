package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Interact01Value implements LazyValue {
    @NotNull
    private final LazyValue protocol;
    @NotNull
    private final LazyValue state;

    public Interact01Value(
            @NotNull final LazyValue protocol,
            @NotNull final LazyValue state) {
        this.protocol = protocol;
        this.state = state;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue vector) {
        return new ApplyValue(
                new ApplyValue(
                        F382Value.INSTANCE,
                        protocol),
                new ApplyValue(
                        new ApplyValue(
                                protocol,
                                state),
                        vector));
    }

    @Override
    public String toString() {
        return "((interact " + protocol + ") " + state + ")";
    }
}
