package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.S3Value;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class SNode implements ASTNode {
    static final ASTNode INSTANCE = new SNode();

    private SNode() {
    }

    @Override
    public String toString() {
        return "s";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return S3Value.INSTANCE;
    }
}