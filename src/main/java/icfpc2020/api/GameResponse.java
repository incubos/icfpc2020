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

    public boolean success;
    public GameStage gameStage;
    public StaticGameInfo staticGameInfo;
    public GameState gameState;

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
                    GameStage.values()[((BigInteger) list.get(1)).intValue()];
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

