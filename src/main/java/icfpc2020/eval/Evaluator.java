package icfpc2020.eval;

import icfpc2020.eval.ast.ASTNode;
import icfpc2020.eval.ast.Declaration;
import icfpc2020.eval.ast.DeclarationParser;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.UndefinedValue;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Evaluates a program.
 *
 * @author incubos
 */
public final class Evaluator implements Universe {
    private static final Logger log = LoggerFactory.getLogger(Evaluator.class);
    private final Map<String, ASTNode> declarations = new HashMap<>();
    private final Map<String, LazyValue> values = new HashMap<>();

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

    public Evaluator(@NotNull final String code) throws IOException {
        this(new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8)));
    }

    @NotNull
    public Evaluator add(@NotNull final String code) throws IOException {
        final BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(
                                        code.getBytes(StandardCharsets.UTF_8))));
        String line;
        while ((line = reader.readLine()) != null) {
            final Declaration declaration = DeclarationParser.parse(line);
            if (declarations.put(declaration.name, declaration.node) != null) {
                log.error("duplicate declaration for name {}, node {}", declaration.name, declaration.name);
                throw new IllegalStateException("Duplicate declaration: " + declaration.name);
            }
        }

        return this;
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
        System.out.println(evaluator.getValue("test").asConst());
    }

    @NotNull
    @Override
    public LazyValue getValue(@NotNull final String name) {
        final LazyValue cached = values.get(name);
        if (cached != null) {
            return cached;
        }

        final ASTNode node = declarations.get(name);
        if (node == null) {
            final LazyValue undefined = new UndefinedValue(name);
            values.put(name, undefined);
            return undefined;
        }
        final LazyValue result = node.eval(this);
        values.put(name, result);
        return result;
    }

    @Override
    public String toString() {
        return declarations.entrySet().stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
