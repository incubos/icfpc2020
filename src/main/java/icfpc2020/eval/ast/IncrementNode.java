package icfpc2020.eval.ast;

import icfpc2020.eval.value.IncrementValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * {@code inc}.
 *
 * @author incubos
 */
final class IncrementNode implements ASTNode {
    @Override
    public String toString() {
        return "inc";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return new IncrementValue();
    }
}