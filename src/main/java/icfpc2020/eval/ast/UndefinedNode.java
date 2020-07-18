package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.UndefinedValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
public final class UndefinedNode implements ASTNode {

    @NotNull
    private final String name;
    public UndefinedNode(@NotNull final String name) { this.name = name; }

    @Override
    public String toString() {
        return "undefined " + name;
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return new UndefinedValue(name);
    }
}