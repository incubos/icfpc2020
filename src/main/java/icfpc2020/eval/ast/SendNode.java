package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.SendValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author incubos
 */
final class SendNode implements ASTNode {
    static final ASTNode INSTANCE = new SendNode();

    private SendNode() {
    }

    @Override
    public String toString() {
        return "send";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return SendValue.INSTANCE;
    }
}