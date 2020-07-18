package icfpc2020.eval.ast;

import icfpc2020.eval.value.C3Value;
import icfpc2020.eval.value.IValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

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
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return IValue.INSTANCE;
    }
}