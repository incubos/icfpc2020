package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.Cons2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author avolokhov
 */
final class ConsNode implements ASTNode {
    static final ASTNode INSTANCE = new ConsNode();

    private ConsNode() {
    }

    @Override
    public String toString() {
        return "cons";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Cons2Value.INSTANCE;
    }
}
