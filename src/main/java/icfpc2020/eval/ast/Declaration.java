package icfpc2020.eval.ast;

import org.jetbrains.annotations.NotNull;

/**
 * Named {@link ASTNode}.
 *
 * @author incubos
 */
public final class Declaration {
    @NotNull
    public final String name;
    @NotNull
    public final ASTNode node;

    public Declaration(
            @NotNull final String name,
            @NotNull final ASTNode node) {
        assert !name.isEmpty();

        this.name = name;
        this.node = node;
    }
}
