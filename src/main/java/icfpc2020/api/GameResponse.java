package icfpc2020.api;

import icfpc2020.list.MList;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author alesavin
 */
public class GameResponse {

    boolean success;
    GameStage gameStage;
    StaticGameInfo staticGameInfo;
    GameState gameState;

    public GameResponse(MList list) {
        if (list.getList().size() < 1 ||
                list.getChild(0).isList() ||
                !list.getChild(0).getValue().equals(BigInteger.ONE)) {
            success = false;
        } else {
            success = true;
            gameStage = GameStage.values()[list.getChild(1).getValue().intValue()];
            if (!list.getChild(2).isNil()) {
                staticGameInfo = new StaticGameInfo(list.getChild(2));
            }
            if (!list.getChild(3).isNil()) {
                gameState = new GameState(list.getChild(3));
            }
        }
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "success=" + success +
                ", gameStage=" + gameStage +
                ", staticGameInfo=" + staticGameInfo +
                ", gameState=" + gameState +
                '}';
    }
}

enum GameStage {
    NOT_STARTED,
    STARTED,
    FINISHED
}

enum Role {
    ATTACKER,
    DEFENDER
}

// staticGameInfo staticGameInfo = (x0, role, x2, x3, x4)
class StaticGameInfo {
    Role role;

    public StaticGameInfo(MList list) {
        role = Role.values()[list.getList().get(1).getValue().intValue()];
    }

    @Override
    public String toString() {
        return "StaticGameInfo{" +
                "role=" + role +
                '}';
    }
}
// gameState = (gameTick, x1, shipsAndCommands)
class GameState {
    BigInteger gameTick;
    Map<Ship, List<Command>> shipsAndCommands;

    public GameState(MList list) {
        gameTick = list.getList().get(0).getValue();
        shipsAndCommands = new HashMap<>();
        list.getList().get(2).getList().forEach( l -> {
            Ship ship = new Ship(l.getList().get(0));
            List<Command> commands = l.getList().get(1).getList()
                    .stream().map(Command::new).collect(Collectors.toList());
            shipsAndCommands.put(ship, commands);
        });
    }

    @Override
    public String toString() {
        return "GameState{" +
                "gameTick=" + gameTick +
                ", shipsAndCommands=" + shipsAndCommands +
                '}';
    }
}

// ship = (role, shipId, position, velocity, x4, x5, x6, x7)
class Ship {
    final Role role;
    final BigInteger shipId;
    final int[] position;
    final int[] velocity;

    public Ship(MList list) {
        role = Role.values()[list.getList().get(0).getValue().intValue()];
        shipId = list.getList().get(1).getValue();
        position = list.getList().get(2).getList()
                .stream().mapToInt(l -> l.getValue().intValue())
                .toArray();
        velocity = list.getList().get(3).getList()
                .stream().mapToInt(l -> l.getValue().intValue())
                .toArray();
    }

    @Override
    public String toString() {
        return "Ship{" +
                "role=" + role +
                ", shipId=" + shipId +
                ", position=" + Arrays.toString(position) +
                ", velocity=" + Arrays.toString(velocity) +
                '}';
    }
}

// (type, shipId, ...)
class Command {

    BigInteger type;
    BigInteger shipId;

    public Command(MList list) {
        type = list.getChild(0).getValue();
        shipId = list.getChild(0).getValue();
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", shipId=" + shipId +
                '}';
    }
}

