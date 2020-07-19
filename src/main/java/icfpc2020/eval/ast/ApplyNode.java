package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.ApplyValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class ApplyNode implements ASTNode {
    @NotNull
    private final ASTNode function;
    @NotNull
    private final ASTNode argument;

    ApplyNode(
            @NotNull final ASTNode function,
            @NotNull final ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "ap " + function.toString() + " " + argument.toString();
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return new ApplyValue(
                function.eval(universe),
                argument.eval(universe));
    }
}
