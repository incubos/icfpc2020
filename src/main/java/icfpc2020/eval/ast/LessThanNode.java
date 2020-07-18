package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.LessThan2Value;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class LessThanNode implements ASTNode {
    static final ASTNode INSTANCE = new LessThanNode();

    private LessThanNode() {
    }

    @Override
    public String toString() {
        return "lt";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return LessThan2Value.INSTANCE;
    }
}