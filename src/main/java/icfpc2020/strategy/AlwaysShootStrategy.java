package icfpc2020.strategy;

import icfpc2020.Commands;
import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class AlwaysShootStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(RandomCommandStrategy.class);

    @Override
    public List<List<Tokens.Token>> next(GameResponse gameResponse) {
        try {
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
            return List.of(Commands.shoot(myShipId.toString(), enemyShip.position, "0"));
        } catch (Throwable t) {
            log.error("Unexpected error", t);
            return Collections.emptyList();
        }
    }
}
