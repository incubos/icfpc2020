package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.NilValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class NilNode implements ASTNode {
    static final ASTNode INSTANCE = new NilNode();

    private NilNode() {
    }

    @Override
    public String toString() {
        return "nil";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return NilValue.INSTANCE;
    }

    @Override
    public boolean needArgumentEvaluation() {
        return false;
    }
}