package icfpc2020;

import icfpc2020.eval.Evaluator;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.NilValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIInteract {
    private static final Logger log = LoggerFactory.getLogger(APIInteract.class);

    public static void main(String[] args) throws Exception {
        final Evaluator galaxy =
                new Evaluator(
                        APIInteract.class.getResourceAsStream("/galaxy.txt"));
        galaxy.add("click0 = ap ap cons 0 0");
        final LazyValue state0 =
                galaxy.add("s0 = ap ap ap interact galaxy nil click0")
                        .getValue("s0")
                        .force();
        System.out.println(state0);

/*
        final LazyValue protocol = galaxy.getValue("galaxy");
        LazyValue state = NilValue.INSTANCE;
        final LazyValue click =
                new Evaluator("click = ap ap cons 0 0")
                        .getValue("click")
                        .eval();
*/


        final LazyValue interact = galaxy.getValue("interact");
    }
}
