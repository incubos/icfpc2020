package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.DemodulateValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

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
    public LazyValue eval(@NotNull final Universe universe) {
        return DemodulateValue.INSTANCE;
    }
}
