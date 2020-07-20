package icfpc2020.strategy;

import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;

import java.util.List;

public class NoopStragety implements Strategy {
    @Override
    public List<List<Tokens.Token>> next(final GameResponse gameResponse) {
        return List.of();
    }
}
