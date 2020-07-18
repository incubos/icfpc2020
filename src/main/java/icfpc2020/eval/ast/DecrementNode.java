package icfpc2020.eval.ast;

import icfpc2020.eval.value.DecrementValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class DecrementNode implements ASTNode {
    static final ASTNode INSTANCE = new DecrementNode();

    private DecrementNode() {
    }

    @Override
    public String toString() {
        return "dec";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return DecrementValue.INSTANCE;
    }
}