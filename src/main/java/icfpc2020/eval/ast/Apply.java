package icfpc2020.eval.ast;

import org.jetbrains.annotations.NotNull;

/**
 * {@code ap}.
 *
 * @author incubos
 */
final class Apply implements ASTNode {
    @NotNull
    private final ASTNode function;
    @NotNull
    private final ASTNode argument;

    Apply(
            @NotNull final ASTNode function,
            @NotNull final ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "ap " + function.toString() + " " + argument.toString();
    }
}
