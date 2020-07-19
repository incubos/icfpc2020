package icfpc2020.eval.value;

import icfpc2020.Message;
import org.jetbrains.annotations.NotNull;

public class BinaryValue implements LazyValue {
    @NotNull
    private final Message value;

    public BinaryValue(@NotNull final Message value) {
        this.value = value;
    }

    @Override
    public Message asBinary() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
