package icfpc2020.galaxy;

import icfpc2020.Command;
import org.jetbrains.annotations.NotNull;

public class GalaxyParser {
    @NotNull
    public Assign parseTextLine(String line) {
        return parseLine(line.split(" "));
    }

    static class ParseTokens {
         final String[] tokens;
         int offset;

        ParseTokens(final String[] tokens, final int offset) {
            this.tokens = tokens;
            this.offset = offset;
        }

        public String fetch(int i) {
            if (offset + i >= tokens.length) {
                return null;
            }
            return tokens[offset + i];
        }

        public void advance(int i) {
            offset += i;
        }
    }

    private Assign parseLine(final String[] tokens) {
        return new Assign(new Atom(tokens[0]), parseCommand(new ParseTokens(tokens, 2)));
    }

    public static Expr parseCommand(ParseTokens commandTokens) {
        final String token = commandTokens.fetch(0);
        commandTokens.advance(1);
        if (Command.App.toString().equals(token)) {
            return new Ap(parseCommand(commandTokens), parseCommand(commandTokens));
        }
        return new Atom(token);
    }
}
