package icfpc2020.eval.value;

import icfpc2020.Message;
import icfpc2020.eval.Universe;
import icfpc2020.eval.ast.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

/**
 * @author incubos
 */
public final class LazyNodeValue implements LazyValue {
    @NotNull
    private final Universe universe;
    @NotNull
    private final ASTNode node;

    private LazyValue cachedEval = null;

    public LazyNodeValue(
            @NotNull final Universe universe,
            @NotNull final ASTNode node) {
        this.node = node;
        this.universe = universe;
    }

    @NotNull
    @Override
    public LazyValue apply(@NotNull final LazyValue arg) {
        return eval().apply(arg);
    }

    @Override
    public LazyValue eval() {
        if (cachedEval == null) {
            cachedEval = node.eval(universe);
        }
        return cachedEval;
    }

    @Override
    public Message asBinary() {
        return eval().asBinary();
    }

    @Override
    public BigInteger asConst() {
        return eval().asConst();
    }

    @Override
    public String toString() {
        return node.toString();
    }
}
