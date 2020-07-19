package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.Pwr2Value;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class Pwr2Node implements ASTNode {
    static final ASTNode INSTANCE = new Pwr2Node();

    private Pwr2Node() {
    }

    @Override
    public String toString() {
        return "pwr2";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Pwr2Value.INSTANCE;
    }
}