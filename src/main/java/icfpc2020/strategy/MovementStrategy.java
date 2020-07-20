package icfpc2020.strategy;

import icfpc2020.Commands;
import icfpc2020.Draw;
import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;
import icfpc2020.geom.Geometer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovementStrategy implements Strategy {

    /**
     * 0 - 32 - run from the sun
     * // Accelerate in between
     * 64 - 128 - run towards the sun
     */
    private final int run;
    private final int towards;
    private final int maxVelocity;

    private static final Logger log = LoggerFactory.getLogger(MovementStrategy.class);

    // Check last 3 points to detect clockwise/counterclockwise
    private final List<Draw.Coord> trace = new ArrayList<>();

    public MovementStrategy() {
        this(32, 128, 10);
    }

    public MovementStrategy(final int run, final int towards, final int maxVelocity) {
        this.run = run;
        this.towards = towards;
        this.maxVelocity = maxVelocity;
    }

    @Override
    public List<List<Tokens.Token>> next(GameResponse gameResponse) {
        try {
            var step = gameResponse.gameState.gameTick;
            var role = gameResponse.staticGameInfo.role;
            var myShip = gameResponse
                    .gameState
                    .shipsAndCommands
                    .keySet()
                    .stream()
                    .filter(s -> s.role == role)
                    .findFirst()
                    .get();

            // Save last 3 moves
            if (trace.size() >= 3) {
                trace.remove(0);
            }
            trace.add(myShip.position);
            boolean clockWise = Geometer.clockWise(trace);
            long x = myShip.position.x;
            long y = myShip.position.y;
            long vx = myShip.velocity.x;
            long vy = myShip.velocity.y;
            long ax;
            long ay;
            long xMirrow = x < 0 ? -1 : 1;
            if (xMirrow == -1) {
                clockWise = !clockWise;
                vx = -vx;
            }
            long yMirrow = y < 0 ? -1 : 1;
            if (yMirrow == -1) {
                clockWise = !clockWise;
                vy = -vy;
            }
            // Now that we are in positive quadrant try to figure out the strategy
            // Run from
            if (x < run && y < run) {
                // TODO: do not ignore velocity!
                ax = -x;
                ay = -y;
            } else if (x < towards && y < towards) { // Run parallel
                if (Math.abs(vx) + Math.abs(vy) > maxVelocity) {
                    // Ignore, we are fine!
                    return Collections.emptyList();
                }
                if (x > run) {
                    ay = 0;
                    ax = clockWise ? -1 : 1;
                } else {
                    ax = 0;
                    ay = clockWise ? -1 : 1;
                }
            } else { // Run towards
                ax = x;
                ay = y;
            }
            // Apply mirrows back to the decision
            return List.of(Commands.accelerate(myShip.shipId.toString(), Draw.Coord.of(ax * xMirrow, ay * yMirrow)));
        } catch (Throwable t) {
            log.error("Unexpected error", t);
            return Collections.emptyList();
        }
    }
}
