package icfpc2020.strategy;

import icfpc2020.Commands;
import icfpc2020.Tokens;
import icfpc2020.api.GameResponse;
import icfpc2020.eval.value.DemodulateValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeStrategy implements Strategy {
    private final List<Strategy> strategies;

    public CompositeStrategy(final List<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public List<List<Tokens.Token>> next(final GameResponse gameResponse) {
        List<List<Tokens.Token>> commands = new ArrayList<>();
        
        for (Strategy strategy: strategies){
            List<List<Tokens.Token>> next = strategy.next(gameResponse);
            // [[0,1,(1,1)],[]]
            commands.addAll(next);
        }
        System.out.println(commands);
        try {
            System.out.println(DemodulateValue.eval(Commands.commands("0", commands)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }
}
