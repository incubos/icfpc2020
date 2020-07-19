package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.C3Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

final class CNode implements ASTNode {
    static final ASTNode INSTANCE = new CNode();

    private CNode() {
    }

    @Override
    public String toString() {
        return "c";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return C3Value.INSTANCE;
    }
}