package icfpc2020.eval.ast;

import icfpc2020.eval.value.Add2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class AddNode implements ASTNode {
    static final ASTNode INSTANCE = new AddNode();

    private AddNode() {
    }

    @Override
    public String toString() {
        return "add";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return Add2Value.INSTANCE;
    }
}