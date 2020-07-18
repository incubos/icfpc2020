package icfpc2020.eval.ast;

import icfpc2020.eval.value.Divide2Value;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * {@code div}.
 *
 * @author incubos
 */
final class DivideNode implements ASTNode {
    @Override
    public String toString() {
        return "div";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return new Divide2Value();
    }
}