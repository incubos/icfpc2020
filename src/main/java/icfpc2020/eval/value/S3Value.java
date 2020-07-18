package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public class S3Value implements LazyValue {
    public static final LazyValue INSTANCE = new S3Value();

    private S3Value() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new S2Value(arg);
    }

    @Override
    public String toString() {
        return "s";
    }
}
