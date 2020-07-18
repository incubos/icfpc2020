package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.NegateValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class NegateNode implements ASTNode {
    static final ASTNode INSTANCE = new NegateNode();

    private NegateNode() {
    }

    @Override
    public String toString() {
        return "neg";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return NegateValue.INSTANCE;
    }
}