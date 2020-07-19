package icfpc2020;

import icfpc2020.eval.Evaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIInteract {
    private static final Logger log = LoggerFactory.getLogger(APIInteract.class);

    public static void main(String[] args) throws Exception {
        final Evaluator galaxy =
                new Evaluator(
                        APIInteract.class.getResourceAsStream("/galaxy.txt"));
        final String click0 = "click0";
        galaxy.add(click0 + " = ap ap cons 0 0");

        String previousState = "nil";
        for (int step = 0; step < 10; step++) {
            final String state = "state" + step;

            // Interact
            log.info("Running step #{}...", step);
            final String interact =
                    String.format(
                            "%s = ap ap ap interact galaxy %s %s",
                            state, previousState, click0);
            final String result = galaxy.add(interact).getValue(state).force().toString();
            log.info("Step #{} result: {}", step, result);

            // Draw
            final String draw = "draw" + step;
            final String print =
                    String.format(
                            "%s = ap cdr %s",
                            draw, state);
            galaxy.add(print).getValue(draw).force();

            // Advance
            previousState = "ap car " + state;
        }
    }
}
