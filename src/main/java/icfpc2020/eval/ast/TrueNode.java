package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.True2Value;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class TrueNode implements ASTNode {
    static final ASTNode INSTANCE = new TrueNode();

    private TrueNode() {
    }

    @Override
    public String toString() {
        return "t";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return True2Value.INSTANCE;
    }
}