package icfpc2020.eval.ast;

import icfpc2020.eval.value.ApplyValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * {@code ap}.
 *
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
    public LazyValue eval() {
        return new ApplyValue(function.eval(), argument.eval());
    }
}