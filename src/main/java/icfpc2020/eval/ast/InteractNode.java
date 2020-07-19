package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.Interact03Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class InteractNode implements ASTNode {
    static final ASTNode INSTANCE = new InteractNode();

    private InteractNode() {
    }

    @Override
    public String toString() {
        return "interact";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Interact03Value.INSTANCE;
    }
}