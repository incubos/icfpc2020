package icfpc2020.eval.ast;

import icfpc2020.eval.value.CdrValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class CdrNode implements ASTNode {
    static final ASTNode INSTANCE = new CdrNode();

    private CdrNode() {
    }

    @Override
    public String toString() {
        return "cdr";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return CdrValue.INSTANCE;
    }
}
