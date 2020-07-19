package icfpc2020;

import icfpc2020.eval.Evaluator;
import icfpc2020.galaxy.Clicker;
import icfpc2020.galaxy.ClickerRoundabout;
import icfpc2020.galaxy.RepeatClicker;
import icfpc2020.galaxy.Vect;
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
        final Clicker clicker = new RepeatClicker(ClickerRoundabout::new, 1600);

        String previousState = "nil";
        for (int step = 0; step < 8192; step++) {
            final String state = "state" + step;
            final String click = "click" + step;

            // Interact
            log.info("Running step #{}...", step);
            final String interact =
                    String.format(
                            "%s = ap ap ap interact galaxy %s %s",
                            state, previousState, click);
            galaxy.add(interact).getValue(state).force();
            //log.info("Step #{} result: {}", step, result);

            // Next click
            final Vect v = clicker.nextClick();
            galaxy.add(String.format(
                    "%s = ap ap cons %d %d",
                    "click" + (step + 1), v.X.intValue(), v.Y.intValue()));

            // Advance
            previousState = "ap car " + state;
        }
    }
}
