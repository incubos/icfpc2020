package icfpc2020.strategy;

import icfpc2020.Commands;
import icfpc2020.Draw;
import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;
import icfpc2020.api.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * TODO
 *
 * @author alesavin
 */
public class RandomCommandStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(RandomCommandStrategy.class);

    Draw.Coord[] coords = new Draw.Coord[]{Draw.Coord.of(1, 1),
            Draw.Coord.of(1, -1), Draw.Coord.of(-1, -1), Draw.Coord.of(-1, 1)};

    Random random = new Random();

    @Override
    public List<List<Tokens.Token>> next(GameResponse gameResponse) {
        try {
            var step = gameResponse.gameState.gameTick;
            var role = gameResponse.staticGameInfo.role;
            var myShipId = gameResponse
                    .gameState
                    .shipsAndCommands
                    .keySet()
                    .stream()
                    .filter(s -> s.role == role)
                    .map(s -> s.shipId)
                    .findFirst()
                    .get();
            var enemyShip = gameResponse
                    .gameState
                    .shipsAndCommands
                    .keySet()
                    .stream()
                    .filter(s -> s.role != role)
                    .findFirst()
                    .get();
            List<List<Tokens.Token>> variants = new ArrayList<>();
            variants.addAll(new RandomAccelerateStrategy().next(gameResponse));
            if (role == Role.ATTACKER) {
                variants.add(Commands.shoot(enemyShip.shipId.toString(), enemyShip.position, "0"));
            }
            return List.of(variants.get(random.nextInt(variants.size())));
        } catch (Throwable t) {
            log.error("Unexpected error", t);
            return Collections.emptyList();
        }
    }
}
