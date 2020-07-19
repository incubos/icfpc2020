package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.ModemValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author incubos
 */
final class ModemNode implements ASTNode {
    static final ASTNode INSTANCE = new ModemNode();

    private ModemNode() {
    }

    @Override
    public String toString() {
        return "modem";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Universe universe) {
        return ModemValue.INSTANCE;
    }
}