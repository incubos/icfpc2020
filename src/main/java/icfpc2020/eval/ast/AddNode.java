package icfpc2020.eval.ast;

import icfpc2020.eval.value.Add2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * {@code add}.
 *
 * @author incubos
 */
final class AddNode implements ASTNode {
    @Override
    public String toString() {
        return "add";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return new Add2Value();
    }
}