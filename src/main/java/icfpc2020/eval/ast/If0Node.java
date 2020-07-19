package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.If03Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class If0Node implements ASTNode {
    static final ASTNode INSTANCE = new If0Node();

    private If0Node() {
    }

    @Override
    public String toString() {
        return "if0";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return If03Value.INSTANCE;
    }
}