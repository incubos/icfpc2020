package icfpc2020.eval.ast;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Parses a declaration.
 *
 * @author incubos
 */
public final class DeclarationParser {
    @NotNull
    public static Declaration parse(@NotNull final String code) {
        final int eq = code.indexOf('=');
        if (eq == -1) {
            throw new IllegalArgumentException("No declaration delimiter");
        }
        final String name = code.substring(0, eq).trim();
        final String body = code.substring(eq + 1).trim();
        final Iterator<String> tokens = Arrays.asList(body.split(" ")).iterator();
        return new Declaration(name, parse(tokens));
    }

    @NotNull
    private static ASTNode parse(@NotNull final Iterator<String> tokens) {
        if (!tokens.hasNext()) {
            throw new IllegalArgumentException("EOF");
        }

        final String token = tokens.next();

        switch (token) {
            case "ap":
                return new ApplyNode(parse(tokens), parse(tokens));
            case "neg":
                return NegateNode.INSTANCE;
            case "inc":
                return IncrementNode.INSTANCE;
            case "dec":
                return DecrementNode.INSTANCE;
            case "add":
                return AddNode.INSTANCE;
            case "mul":
                return MultiplyNode.INSTANCE;
            case "div":
                return DivideNode.INSTANCE;
            case "eq":
                return EqualityNode.INSTANCE;
            case "t":
                return TrueNode.INSTANCE;
            case "f":
                return FalseNode.INSTANCE;
            default:
                try {
                    return new ConstantNode(new BigInteger(token));
                } catch (NumberFormatException e) {
                    return new VariableNode(token);
                }
        }
    }
}
