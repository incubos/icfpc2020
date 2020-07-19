package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class VariableNode implements ASTNode {
    @NotNull
    private final String name;

    VariableNode(@NotNull final String name) {
        assert !name.isEmpty();
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        // Resolve reference
        return universe.getValue(name);
    }
}