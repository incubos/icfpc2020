package icfpc2020.eval.ast;

import icfpc2020.eval.Universe;
import icfpc2020.eval.value.CarValue;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

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
    public LazyValue eval(@NotNull final Universe universe) {
        return CarValue.INSTANCE;
    }
}
