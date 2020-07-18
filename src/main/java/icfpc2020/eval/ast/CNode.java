package icfpc2020.eval.ast;

import icfpc2020.eval.value.C3Value;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.S3Value;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

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
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return C3Value.INSTANCE;
    }
}