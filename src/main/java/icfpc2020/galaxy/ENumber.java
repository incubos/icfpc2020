package icfpc2020.galaxy;

import java.math.BigInteger;
import java.util.Map;

public class ENumber implements EvalResult {
    final BigInteger number;

    ENumber(BigInteger number) {
        this.number = number;
    }

    @Override
    public EvalResult eval(Map<String, EvalResult> universe) {
        return this;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
