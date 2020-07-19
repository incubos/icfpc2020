package icfpc2020.galaxy;

import icfpc2020.Command;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GalaxyParser {
    @NotNull
    public Assign parseTextLine(String line) {
        final List<Object> tokens = Arrays.stream(line.split(" ")).map(t -> {
            for (final Command command : Command.values()) {
                if (t.equals(command.toString())) {
                    return command;
                }
            }
            if (Pattern.matches("-?\\d+", t)) {
                return new BigInteger(t);
            }
            return t;
        }).collect(Collectors.toList());
        return parseLine(tokens);
    }

    static class ParseTokens {
         final List<Object> tokens;
         int offset;

        ParseTokens(final List<Object> tokens, final int offset) {
            this.tokens = tokens;
            this.offset = offset;
        }

        public Object fetch(int i) {
            if (offset + i >= tokens.size()) {
                return null;
            }
            return tokens.get(offset + i);
        }

        public void advance(int i) {
            offset += i;
        }
    }

    private Assign parseLine(List<Object> tokens) {
        return new Assign(new Atom(tokens.get(0).toString()), parseCommand(new ParseTokens(tokens, 2)));
    }

    private Expr parseCommand(ParseTokens commandTokens) {
        final Object token = commandTokens.fetch(0);
        commandTokens.advance(1);
        if (token instanceof Command) {
            final Command command = (Command) token;
            if (command == Command.App) {
                return new Ap(parseCommand(commandTokens), parseCommand(commandTokens));
            }
        }
        return new Atom(token.toString());
    }
}
