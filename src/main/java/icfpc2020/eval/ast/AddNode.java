package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.Add2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class AddNode implements ASTNode {
    static final ASTNode INSTANCE = new AddNode();

    private AddNode() {
    }

    @Override
    public String toString() {
        return "add";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Add2Value.INSTANCE;
    }
}