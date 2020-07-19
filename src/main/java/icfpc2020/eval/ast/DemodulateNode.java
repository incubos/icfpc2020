package icfpc2020.eval.ast;

import icfpc2020.eval.value.DemodulateValue;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.ModulateValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class DemodulateNode implements ASTNode {
    static final ASTNode INSTANCE = new DemodulateNode();

    private DemodulateNode() {
    }

    @Override
    public String toString() {
        return "dem";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return DemodulateValue.INSTANCE;
    }
}
