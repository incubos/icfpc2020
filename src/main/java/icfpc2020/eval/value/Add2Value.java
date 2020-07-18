package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class Add2Value implements LazyValue {
    @NotNull
    @Override
    public LazyValue apply(final LazyValue arg) {
        return new Add1Value(arg);
    }

    @Override
    public String toString() {
        return "add";
    }
}
