package icfpc2020.eval.ast;

import icfpc2020.eval.value.CarValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class CarNode implements ASTNode {
    static final ASTNode INSTANCE = new CarNode();

    private CarNode() {
    }

    @Override
    public String toString() {
        return "car";
    }

    @NotNull
    @Override
    public LazyValue eval(@NotNull final Function<String, ASTNode> declarations) {
        return CarValue.INSTANCE;
    }
}
