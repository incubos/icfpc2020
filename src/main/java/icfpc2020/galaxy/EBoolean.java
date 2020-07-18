package icfpc2020.galaxy;

import java.util.Map;

public class EBoolean implements EvalResult {
    public final boolean value;

    public EBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        return this;
    }

    @Override
    public String pp() {
        return value ? "t" : "f";
    }

    @Override
    public String toString() {
        return value ? "t" : "f";
    }
}
