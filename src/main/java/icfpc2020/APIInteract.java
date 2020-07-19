package icfpc2020;

import icfpc2020.eval.Evaluator;
import icfpc2020.eval.value.LazyValue;
import icfpc2020.eval.value.NilValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIInteract {
    private static final Logger log = LoggerFactory.getLogger(APIInteract.class);

    public static void main(String[] args) throws Exception {
        final LazyValue click =
                new Evaluator("click = ap ap cons 0 0")
                        .getValue("click")
                        .eval();
        LazyValue state = NilValue.INSTANCE;
        final LazyValue protocol =
                new Evaluator(
                        APIInteract.class.getResourceAsStream("/galaxy.txt"))
                        .getValue("galaxy");
    }
}
