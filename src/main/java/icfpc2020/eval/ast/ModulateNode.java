package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.ModulateValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ModulateNode implements ASTNode{
    static final ASTNode INSTANCE = new ModulateNode();

    private ModulateNode() {
    }

    @Override
    public String toString() {
        return "mod";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return ModulateValue.INSTANCE;
    }
}
