package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class MultipledrawValue implements LazyValue {
    public static final LazyValue INSTANCE = new MultipledrawValue();

    private MultipledrawValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        var input = arg.force();
        if (input instanceof NilValue) {
            return NilValue.INSTANCE;
        } else if (input instanceof ConsValue) {
            ConsValue consValue = (ConsValue) input;
            return new ConsValue(
                    new ApplyValue(DrawValue.INSTANCE, consValue.left).force(),
                    apply(consValue.right)
            );
        } else {
            throw new UnsupportedOperationException("multipledraw only support list of lists");
        }
    }

    @Override
    public String toString() {
        return "multipledraw";
    }
}
