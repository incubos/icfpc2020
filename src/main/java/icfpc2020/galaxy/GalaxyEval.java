package icfpc2020.galaxy;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GalaxyEval {

    @NotNull
     public Map<String, EvalResult> eval(final Reader reader, final String variable) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final Map<String, EvalResult> universe = new HashMap<>();
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            final EAssign assign = new GalaxyParser().parseTextLine(line);
            assign.eval(universe);
            System.out.println(assign.toString());
        }
        String result = null;
        while (true) {
            // Dereference variable
            final EvalResult newResult = universe.get(variable).eval(universe);
            universe.put(variable, newResult);
            final String newResultStr = newResult.toString();
            if (newResultStr.equals(result)) {
                break;
            } else {
                result = newResultStr;
            }
        }
        return universe;
    }


}
