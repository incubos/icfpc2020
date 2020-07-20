package icfpc2020.api;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// gameState = (gameTick, x1, shipsAndCommands)
public class GameState {
    public BigInteger gameTick;
    public Map<Ship, List<Command>> shipsAndCommands;

    public GameState(List<Object> list) {
        System.out.println(list);
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
