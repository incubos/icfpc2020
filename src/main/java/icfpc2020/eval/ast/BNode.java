package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.B3Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

final class BNode implements ASTNode {
    static final ASTNode INSTANCE = new BNode();

    private BNode() {
    }

    @Override
    public String toString() {
        return "b";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return B3Value.INSTANCE;
    }
}