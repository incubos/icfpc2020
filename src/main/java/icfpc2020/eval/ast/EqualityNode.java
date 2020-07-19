package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.Equality2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class EqualityNode implements ASTNode {
    static final ASTNode INSTANCE = new EqualityNode();

    private EqualityNode() {
    }

    @Override
    public String toString() {
        return "eq";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Equality2Value.INSTANCE;
    }
}