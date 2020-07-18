package icfpc2020.eval.ast;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * {@code ap}.
 *
 * @author incubos
 */
final class Constant implements ASTNode {
    @NotNull
    private final BigInteger value;

    Constant(@NotNull final BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
