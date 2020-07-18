package icfpc2020.eval.ast;

import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;

/**
 * Tree node.
 *
 * @author incubos
 */
public interface ASTNode {
    @NotNull
    LazyValue eval(LazyValue... args);
}
