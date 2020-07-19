package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.False2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class FalseNode implements ASTNode {
    static final ASTNode INSTANCE = new FalseNode();

    private FalseNode() {
    }

    @Override
    public String toString() {
        return "f";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return False2Value.INSTANCE;
    }
}