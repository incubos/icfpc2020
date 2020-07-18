package icfpc2020.galaxy;

import java.util.Map;

public class EAssign implements EvalResult {
     final String name;
     final EvalResult right;

    EAssign(final String name, final EvalResult right) {
        this.name = name;
        this.right = right;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        universe.put(name, right);
        return this;
    }

    @Override
    public String pp() {
        return name + " = " + right.pp();
    }

    @Override
    public String toString() {
       return name + " = " + right.toString();
    }
}
