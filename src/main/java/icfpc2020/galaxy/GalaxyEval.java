package icfpc2020.galaxy;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GalaxyEval {
    public Map<String, EvalResult> eval(StringBuilder stringBuilder) throws IOException {
        return eval(new InputStreamReader(GalaxyEval.class.getResourceAsStream("/galaxy.txt")));
    }

    @NotNull
     public Map<String, EvalResult> eval(Reader reader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final Map<String, EvalResult> universe = new HashMap<>();
        final List<String> variablesOrder = new ArrayList<>();
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            final EAssign lineEval = new GalaxyParser().parseTextLine(line);
            lineEval.eval(universe);
            variablesOrder.add(lineEval.name);
        }
        while (variablesOrder.size() != 0) {
            final String val = variablesOrder.get(variablesOrder.size() - 1);
            // Dereference variable
            final EvalResult result = universe.get(val).eval(universe);
            universe.put(val, result);
            variablesOrder.remove(val);
        }
        return universe;
    }


    public static String toText(Map<String, EvalResult> universe) {
        return universe.entrySet().stream()
                .sorted((Comparator.comparing(Map.Entry::getKey))).map((e ->
                e.getKey() + " = " + e.getValue().toString())).collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {
        final Map<String, EvalResult> resultMap = new GalaxyEval().eval(new StringReader("variable = 1"));
        System.out.println(toText(resultMap));
    }
}
