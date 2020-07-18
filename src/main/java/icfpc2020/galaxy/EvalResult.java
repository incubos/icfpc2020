package icfpc2020.galaxy;

import icfpc2020.Command;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface EvalResult {
    default EvalResult eval(Map<String, EvalResult> universe) {
        throw new IllegalStateException("Not implemented");
    }
}


