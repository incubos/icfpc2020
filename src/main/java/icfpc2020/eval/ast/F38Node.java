package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.F382Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

public class F38Node implements ASTNode {
    static final ASTNode INSTANCE = new F38Node();

    private F38Node() {
    }

    @Override
    public String toString() {
        return "f38";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return F382Value.INSTANCE;
    }
}
