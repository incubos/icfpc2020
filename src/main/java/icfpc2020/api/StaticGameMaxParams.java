package icfpc2020.api;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;

/**
 * @author incubos
 */
public final class StaticGameMaxParams {
    @NotNull
    public final BigInteger x0;
    @NotNull
    public final BigInteger x1;
    @NotNull
    public final BigInteger x2;
    @NotNull
    public final BigInteger x3;

    public StaticGameMaxParams(@NotNull final List<Object> list) {
        x0 = (BigInteger) list.get(0);
        x1 = (BigInteger) list.get(1);
        x2 = (BigInteger) list.get(2);
        x3 = (BigInteger) list.get(3);
    }

    @Override
    public String toString() {
        return "StaticGameMaxParams{" +
                "x0=" + x0 +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                '}';
    }
}
