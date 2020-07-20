package icfpc2020.strategy;

import icfpc2020.Commands;
import icfpc2020.Draw;
import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author alesavin
 */
public class RandomAccelerateStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(RandomAccelerateStrategy.class);

    Draw.Coord[] coords = new Draw.Coord[]{Draw.Coord.of(1, 1),
            Draw.Coord.of(1, -1), Draw.Coord.of(-1, -1), Draw.Coord.of(-1, 1)};

    @Override
    public List<List<Tokens.Token>> next(GameResponse gameResponse) {
        try {
            var step = gameResponse.gameState.gameTick;
            var role = gameResponse.staticGameInfo.role;
            var shipId = gameResponse
                    .gameState
                    .shipsAndCommands
                    .keySet()
                    .stream()
                    .filter(s -> s.role == role)
                    .map(s -> s.shipId)
                    .findFirst()
                    .get();

            return List.of(Commands.accelerate(shipId.toString(), coords[step.intValue() % 4]));
        } catch (Throwable t) {
            log.error("Unexpected error", t);
            return Collections.emptyList();
        }
    }
}
