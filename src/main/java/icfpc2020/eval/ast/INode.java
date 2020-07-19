package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.IValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

final class INode implements ASTNode {
    static final ASTNode INSTANCE = new INode();

    private INode() {
    }

    @Override
    public String toString() {
        return "i";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return IValue.INSTANCE;
    }
}