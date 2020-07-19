package icfpc2020.eval.value;

import org.jetbrains.annotations.NotNull;

public class F381Value implements LazyValue {
    @NotNull
    private final LazyValue x2;

    public F381Value(final LazyValue x2) {
        this.x2 = x2;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        LazyValue x0 = arg;
        // ap ap ap if0 ap car x0
        return new ApplyValue(
                new ApplyValue(
                        new ApplyValue(
                                If03Value.INSTANCE,
                                new ApplyValue(
                                        CarValue.INSTANCE,
                                        x0)
                        ),
                        // ( ap modem ap car ap cdr x0
                        new ApplyValue(
                                new ApplyValue(
                                        Cons2Value.INSTANCE,
                                        new ApplyValue(
                                                ModemValue.INSTANCE,
                                                new ApplyValue(
                                                        CarValue.INSTANCE,
                                                        new ApplyValue(
                                                                CdrValue.INSTANCE,
                                                                x0
                                                        )
                                                )
                                        )
                                ),
                                // , ap multipledraw ap car ap cdr ap cdr x0 )
                                new ApplyValue(
                                        new ApplyValue(
                                                Cons2Value.INSTANCE,
                                                new ApplyValue(
                                                        new LazyValue() {}, // MultipeDraw
                                                        new ApplyValue(
                                                                CarValue.INSTANCE,
                                                                new ApplyValue(
                                                                        CdrValue.INSTANCE,
                                                                        new ApplyValue(
                                                                                CdrValue.INSTANCE,
                                                                                x0
                                                                        )
                                                                )
                                                        )
                                                )
                                        ),
                                        NilValue.INSTANCE
                                )
                        )
                ),
                // ap ap ap interact x2 ap modem ap car ap cdr x0 ap send ap car ap cdr ap cdr x0
                new ApplyValue(
                        new ApplyValue(
                                new ApplyValue(
                                        Interact03Value.INSTANCE,
                                        x2
                                ),
                                new ApplyValue(
                                        ModemValue.INSTANCE,
                                        new ApplyValue(
                                                CarValue.INSTANCE,
                                                new ApplyValue(
                                                        CdrValue.INSTANCE,
                                                        x0
                                                )
                                        )
                                )
                        ),
                        new ApplyValue(
                                SendValue.INSTANCE,
                                new ApplyValue(
                                        CarValue.INSTANCE,
                                        new ApplyValue(
                                                CdrValue.INSTANCE,
                                                new ApplyValue(
                                                        CdrValue.INSTANCE,
                                                        x0
                                                )
                                        )
                                )
                        )
                )
        );

    }

    @Override
    public String toString() {
        return "(f38 " + x2 + ")";
    }
}
