package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Tree node.
 *
 * @author incubos
 */
public interface ASTNode {
    @NotNull
    LazyValue eval(@NotNull Function<String, ASTNode> declarations);

    default boolean needArgumentEvaluation() {
        return true;
    }
}
