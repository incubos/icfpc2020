package icfpc2020.eval.ast;

import icfpc2020.eval.value.Equality2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

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
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return Equality2Value.INSTANCE;
    }
}