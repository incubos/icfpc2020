package icfpc2020.galaxy;

import java.util.Map;

class EVariable implements EvalResult {
    private final String name;

    EVariable(final String name) {
        this.name = name;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        if (universe.containsKey(name)) {
            return universe.get(name);
        }
        throw new IllegalStateException("Not expected");
    }

    @Override
    public String toString() {
        return name;
    }
}
