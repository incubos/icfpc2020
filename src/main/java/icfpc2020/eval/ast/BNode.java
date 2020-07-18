package icfpc2020.eval.ast;

import icfpc2020.eval.value.B3Value;
import icfpc2020.eval.value.C3Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

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
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return B3Value.INSTANCE;
    }
}