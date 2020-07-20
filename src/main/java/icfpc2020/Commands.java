package icfpc2020;

import icfpc2020.eval.value.DemodulateValue;
import icfpc2020.eval.value.ModulateValue;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Commands {
    private static final Logger log = LoggerFactory.getLogger(Commands.class);

    public static final Command cons = Command.Cons;
    public static final Command lpar = Command.ListLPar;
    public static final Command comma = Command.ListComma;
    public static final Command rpar = Command.ListRPar;
    public static final Command nil = Command.Nil;

    public static String create() {
        String s = ModulateList.mod2(List.of(lpar, number(1),
                                             comma,
                                             number(0),
                                             rpar)).toString();
        log.debug("create command={}", s);
        return s;
    }

    @NotNull
    private static Tokens.Number number(final long i) {
        return Tokens.Number.of(i);
    }

    @NotNull
    private static Tokens.Number number(final String i) {
        return Tokens.Number.of(i);
    }

    // Should accpet unknown list later
    public static String join(String playerKey) {
        String s = ModulateList.mod2(List.of(lpar, number(2), comma, number(playerKey), comma, nil, rpar)).toString();
        log.debug("Join command for playerKey={} command={}", playerKey, s);
        log.debug("Join command for playerKey={} commands={}", playerKey,
                  DemodulateValue.demodulate(s));
        return s;
    }

    public static String start(String playerKey, String x0, String x1, String x2, String x3) {
        String s = ModulateList.mod2(List.of(lpar, number(3), comma, number(playerKey), comma,
                                             lpar, number(x0), comma, number(x1), comma, number(x2),
                                             comma, number(x3), rpar, rpar)).toString();
        log.debug("Start command for playerKey={} x0={}, x1={}, x2={}, x3={} command={}",
                  playerKey, x0, x1, x2, x3, s);
        log.debug("Start command for playerKey={} commands={}", playerKey,
                  DemodulateValue.demodulate(s));
        return s;
    }

    public static String startNil(String playerKey) {
        String s = ModulateList.mod2(List.of(Commands.lpar, number(3), comma, number(playerKey), comma, nil, rpar)).toString();
        log.debug("Start command for playerKey={} command={}", playerKey, s);
        log.debug("Start command for playerKey={} commands={}", playerKey,
                  DemodulateValue.demodulate(s));
        return s;
    }

    // commands should be modulated via corresponding methods (accelerate/detonate/shoot)
    public static String commands(String playerKey, List<List<Tokens.Token>> commands) {
        List<Tokens.Token> result = new ArrayList<>();
        result.add(lpar);
        result.add(number(4));
        result.add(comma);
        result.add(number(playerKey));
        result.add(comma);
        Iterator<List<Tokens.Token>> iter = commands.iterator();
        while (iter.hasNext()) {
            var command = iter.next();
            result.add(lpar);
            result.addAll(command);
            result.add(rpar);
            if (iter.hasNext()) {
                result.add(comma);
            }
        }
        result.add(rpar);
        String s = ModulateList.mod2(result).toString();
        log.debug("Commands command for playerKey={}, commands={} command={}", playerKey, commands, s);
        return s;
    }

    public static List<Tokens.Token> accelerate(String shipId, Draw.Coord vector) {
        List<Tokens.Token> commands = List.of(Commands.lpar, number(0), comma, number(shipId), comma, cons,
                                              number(vector.x), number(vector.y), rpar);
        log.debug("Accelerate command for shipId={} vector.x={}, vector.y={} command={}",
                  shipId, vector.x, vector.y, commands);
        return commands;
    }

    public static List<Tokens.Token> detonate(String shipId) {
        List<Tokens.Token> commands = List.of(Commands.lpar, number(1), comma, number(shipId), rpar);
        log.debug("Detonate command for shipId={} command={}", shipId, commands);
        return commands;
    }

    public static List<Tokens.Token> shoot(String shipId, Draw.Coord target, String x3) {
        List<Tokens.Token> commands = List.of(Commands.lpar,
                                                number(2),
                                                comma,
                                                 number(shipId), comma, cons,
                                              number(target.x),
                                              number(target.y), comma, number(x3), rpar);
        log.debug("Shoot command for shipId={} target.x={}, target.y={} x3={} command={}",
                  shipId, target.x, target.y, x3, commands);
        return commands;
    }
}
