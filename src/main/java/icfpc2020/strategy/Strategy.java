package icfpc2020.strategy;

import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;

import java.util.List;

/**
 * TODO
 *
 * @author alesavin
 */
public interface Strategy {

    List<List<Tokens.Token>> next(GameResponse gameResponse);
}
