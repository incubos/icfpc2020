package icfpc2020.eval.value;

import icfpc2020.Message;
import icfpc2020.MessageImpl;
import icfpc2020.operators.Modulate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModulateValue implements LazyValue {
    private static final Logger log = LoggerFactory.getLogger(ModulateValue.class);
    public static final LazyValue INSTANCE = new ModulateValue();


//    Command.App, "",
//    Command.Cons, "11",
//    Command.Nil, "00",
//    Command.ListLPar, "11",
//    Command.ListComma, "11",
//    Command.ListRPar, "00"

    private ModulateValue() {
    }

    public void applyInternal(@NotNull final LazyValue arg, final StringBuilder sb) {
        if (arg instanceof ConstantValue) {
            sb.append(Modulate.mod(arg.asConst()).toString());
        } else if (arg instanceof ApplyValue) {
            ApplyValue applyValue = (ApplyValue) arg;
            applyInternal(applyValue.function, sb);
            applyInternal(applyValue.argument, sb);
        } else if (arg instanceof NilValue) {
            sb.append("00");
        } else if (arg instanceof Cons2Value) {
            sb.append("11");
        }
        // HOOK list lPar,comma,rPar
//        else if (nilValue) {
//
//        }
        else {
            log.error(arg.toString());
            throw new UnsupportedOperationException("modulate argument should be modulateable");
        }

    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        final StringBuilder sb = new StringBuilder();
        applyInternal(arg, sb);
//        else if (nilValue) {
//
//        }
        return new BinaryValue(new MessageImpl(sb.toString()));
    }

    @Override
    public String toString() {
        return "mod";
    }
}
