package icfpc2020.eval.value;

import icfpc2020.MessageImpl;
import icfpc2020.operators.Modulate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModulateValue implements LazyValue {
    private static final Logger log = LoggerFactory.getLogger(ModulateValue.class);
    public static final LazyValue INSTANCE = new ModulateValue();


    private ModulateValue() {
    }

    // ap, "",
    // cons, "11",
    // nil, "00",
    public void applyInternal(@NotNull final LazyValue arg, final StringBuilder sb) {
        final LazyValue input = arg.eval();
        if (input instanceof ConstantValue) {
            sb.append(Modulate.modString(input.asConst()));
        } else if (input instanceof ApplyValue) {
            ApplyValue applyValue = (ApplyValue) input;
            applyInternal(applyValue.function, sb);
            applyInternal(applyValue.argument, sb);
        } else if (input instanceof NilValue) {
            sb.append("00");
        } else if (input instanceof Cons2Value) {
            sb.append("11");
        } else {
            log.error("unexpected literal while modulating {}", input.toString());
            throw new UnsupportedOperationException("modulate argument should be modulateable");
        }
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        final StringBuilder sb = new StringBuilder();
        applyInternal(arg, sb);
        return new BinaryValue(new MessageImpl(sb.toString()));
    }

    @Override
    public String toString() {
        return "mod";
    }
}
