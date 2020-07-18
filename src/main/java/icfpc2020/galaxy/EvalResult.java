package icfpc2020.galaxy;

import java.util.Map;

public interface EvalResult {
    default EvalResult eval(Map<String, EvalResult> universe) {
        throw new IllegalStateException("Not implemented");
    }

    String pp();
}


