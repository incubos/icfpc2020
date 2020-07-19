package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.Divide2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class DivideNode implements ASTNode {
    static final ASTNode INSTANCE = new DivideNode();

    private DivideNode() {
    }

    @Override
    public String toString() {
        return "div";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return Divide2Value.INSTANCE;
    }
}