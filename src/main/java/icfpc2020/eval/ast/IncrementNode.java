package icfpc2020.eval.ast;

import icfpc2020.eval.value.IncrementValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * {@code ap}.
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
    public LazyValue eval(final LazyValue... args) {
        assert args.length == 1;
        return new IncrementValue(args[0]);
    }
}