package icfpc2020.eval;

import icfpc2020.eval.ast.ASTNode;
import icfpc2020.eval.ast.Declaration;
import icfpc2020.eval.ast.DeclarationParser;
import icfpc2020.eval.value.LazyValue;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Evaluates a program.
 *
 * @author incubos
 */
public final class Evaluator {
    private static final Logger log = LoggerFactory.getLogger(Evaluator.class);
    private final Map<String, ASTNode> declarations = new HashMap<>();

    public Evaluator(@NotNull final InputStream is) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            final Declaration declaration = DeclarationParser.parse(line);
            if (declarations.put(declaration.name, declaration.node) != null) {
                log.error("duplicate declaration for name {}, node {}", declaration.name, declaration.name);
                throw new IllegalStateException("Duplicate declaration: " + declaration.name);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("File name expected as an argument");
        }

        final Evaluator evaluator;

        // Parse
        try (InputStream is = new FileInputStream(args[0])) {
            evaluator = new Evaluator(is);
        }

        // Print
        System.out.println(evaluator);

        // Evaluate
        System.out.println(evaluator.function("test").asConst());
    }

    @NotNull
    private ASTNode getDeclaration(@NotNull final String name) {
        final ASTNode node = declarations.get(name);
        if (node == null) {
            log.error("No declaration for name {}", name);
            throw new NoSuchElementException();
        }
        return node;
    }

    @NotNull
    public LazyValue function(@NotNull final String name) {
        return getDeclaration(name).eval(this::getDeclaration);
    }

    @Override
    public String toString() {
        return declarations.entrySet().stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
