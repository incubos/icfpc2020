package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.MultipledrawValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author avolokhov
 */
final class MultipledrawNode implements ASTNode {
    static final ASTNode INSTANCE = new MultipledrawNode();

    private MultipledrawNode() {
    }

    @Override
    public String toString() {
        return "multipledraw";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return MultipledrawValue.INSTANCE;
    }
}
