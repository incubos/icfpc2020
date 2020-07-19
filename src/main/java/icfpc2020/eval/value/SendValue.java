package icfpc2020.eval.value;

import icfpc2020.api.PublicAPIImpl;
import icfpc2020.MessageImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
public final class SendValue implements LazyValue {
    public static final LazyValue INSTANCE = new SendValue();

    private SendValue() {
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        final String request =
                new ApplyValue(ModulateValue.INSTANCE, arg)
                        .eval()
                        .asBinary()
                        .toString();
        final String response = PublicAPIImpl.INSTANCE.send(request);
        return new ApplyValue(
                DemodulateValue.INSTANCE,
                new BinaryValue(
                        new MessageImpl(response)));
    }

    @Override
    public String toString() {
        return "send";
    }
}
