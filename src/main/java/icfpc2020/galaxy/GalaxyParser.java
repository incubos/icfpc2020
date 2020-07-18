package icfpc2020.galaxy;

import icfpc2020.Command;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GalaxyParser {
    @NotNull
    public EvalResult parseTextLine(String line) {
        final List<Object> tokens = Arrays.stream(line.split(" ")).map(t -> {
            for (final Command command : Command.values()) {
                if (t.equals(command.toString())) {
                    return command;
                }
            }
            if (Pattern.matches("\\d+", t)) {
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

    private EvalResult parseLine(List<Object> tokens) {
        final Object token0 = tokens.get(0);
        if (token0 == Command.Galaxy) {
            return new EGalaxyAssign(parseCommand(new ParseTokens(tokens, 2)));
        } else {
            final String variable = (String) token0;
            return new EAssign(variable, parseCommand(new ParseTokens(tokens, 2)));
        }
    }

    private EvalResult parseCommand(ParseTokens commandTokens) {
        final Object token = commandTokens.fetch(0);
        if (token instanceof BigInteger) {
            commandTokens.advance(1);
            return new ENumber((BigInteger) token);
        }
        if (token instanceof String) {
            commandTokens.advance(1);
            return new EVariable((String) token);
        }
        if (token instanceof Command) {
            final Command command = (Command) token;
            commandTokens.advance(1);
            final List<EvalResult> args = new ArrayList<>();
            for (int i = 0; i < command.getNumberOfArgs(); i++) {
                args.add(parseCommand(commandTokens));
            }
            return new ECmd(command, args);
        }
        return null;
    }
}
