package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.IsnilValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class IsnilNode implements ASTNode {
    static final ASTNode INSTANCE = new IsnilNode();

    private IsnilNode() {
    }

    @Override
    public String toString() {
        return "isnil";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return IsnilValue.INSTANCE;
    }
}