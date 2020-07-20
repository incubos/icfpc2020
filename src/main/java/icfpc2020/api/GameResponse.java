package icfpc2020.api;

import icfpc2020.Draw;
import icfpc2020.list.MList;

import java.math.BigInteger;
import java.util.ArrayList;
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

//    public GameResponse(MList list) {
//        if (list.getList().size() < 1 ||
//                list.getChild(0).isList() ||
//                !list.getChild(0).getValue().equals(BigInteger.ONE)) {
//            success = false;
//        } else {
//            success = true;
//            gameStage = GameStage.values()[list.getChild(1).getValue().intValue()];
//            if (!list.getChild(2).isNil()) {
//                staticGameInfo = new StaticGameInfo(list.getChild(2));
//            }
//            if (!list.getChild(3).isNil()) {
//                gameState = new GameState(list.getChild(3));
//            }
//        }
//    }


    public GameResponse(List<Object> list) {
        if (list.size() < 1 ||
                list.get(0) instanceof List ||
                !list.get(0).equals(BigInteger.ONE)) {
            success = false;
        } else {
            success = true;
            gameStage =
                    GameStage.values()[((BigInteger) list.get(0)).intValue()];
            if (list.get(2) != null) {
                staticGameInfo = new StaticGameInfo((List<Object>) list.get(2));
            }
            if (list.get(3) != null) {
                gameState = new GameState((List<Object>) list.get(3));
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

    public StaticGameInfo(List<Object> list) {
        role = Role.values()[((BigInteger)list.get(1)).intValue()];
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

    public GameState(List<Object> list) {
        gameTick = ((BigInteger) list.get(0));
        shipsAndCommands = new HashMap<>();
        ((List<Object>) list.get(2)).forEach(l -> {
            List<Object> shipAndCommands = (List<Object>) l;
            Ship ship = new Ship((List<Object>) shipAndCommands.get(0));
            List<Command> commands;
            if (shipAndCommands.get(1) == null){
                commands = new ArrayList<>();
            } else {
                commands = ((List<Object>) shipAndCommands.get(1))
                        .stream().map(o -> ((List<Object>) o)).map(Command::new).collect(Collectors.toList());
            }
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
    final Draw.Coord position;
    final Draw.Coord velocity;

    public Ship(List<Object> list) {
        role = Role.values()[((BigInteger)list.get(0)).intValue()];
        shipId = ((BigInteger)list.get(1));
        position = (Draw.Coord)list.get(2);
        velocity = (Draw.Coord)list.get(3);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "role=" + role +
                ", shipId=" + shipId +
                ", position=" + position +
                ", velocity=" + velocity +
                '}';
    }
}

// (type, shipId, ...)
class Command {

    BigInteger type;
    BigInteger shipId;

    public Command(List<Object> list) {
        type = (BigInteger) list.get(0);
        shipId = (BigInteger) list.get(0);
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", shipId=" + shipId +
                '}';
    }
}

