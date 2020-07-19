package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.DrawValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author avolokhov
 */
final class DrawNode implements ASTNode {
    static final ASTNode INSTANCE = new DrawNode();

    private DrawNode() {
    }

    @Override
    public String toString() {
        return "draw";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return DrawValue.INSTANCE;
    }
}
