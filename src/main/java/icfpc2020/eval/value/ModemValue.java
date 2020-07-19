package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class ModemValue implements LazyValue {
    public static final LazyValue INSTANCE = new ModemValue();

    private ModemValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return new ApplyValue(
                DemodulateValue.INSTANCE,
                new ApplyValue(
                        ModulateValue.INSTANCE,
                        arg));
    }

    @Override
    public String toString() {
        return "modem";
    }
}
