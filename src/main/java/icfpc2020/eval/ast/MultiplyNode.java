package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.Multiply2Value;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class MultiplyNode implements ASTNode {
    static final ASTNode INSTANCE = new MultiplyNode();

    private MultiplyNode() {
    }

    @Override
    public String toString() {
        return "mul";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return Multiply2Value.INSTANCE;
    }
}