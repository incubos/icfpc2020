package icfpc2020.strategy;

import icfpc2020.Commands;
import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;
import icfpc2020.api.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SplitStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(RandomCommandStrategy.class);

    @Override
    public List<List<Tokens.Token>> next(GameResponse gameResponse) {
        try {
            var role = gameResponse.staticGameInfo.role;
            Ship collect = gameResponse
                    .gameState
                    .shipsAndCommands
                    .keySet()
                    .stream()
                    .filter(s -> s.role == role)
                    .max(Comparator.comparing(o -> o.x7))
                    .get();
            if (collect.x7.intValue()>1) {
                return List.of(Commands.split(collect.shipId.toString(), 0, 0, 0, 1));
            } else {
                return List.of();
            }
        } catch (Throwable t) {
            log.error("Unexpected error", t);
            return Collections.emptyList();
        }
    }
}
