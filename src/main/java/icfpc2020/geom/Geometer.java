package icfpc2020.geom;

import icfpc2020.Draw;

import java.math.BigInteger;
import java.util.List;

public class Geometer {
    public static boolean clockWise(final List<Draw.Coord> coords) {
        // Initialize area
        BigInteger area = BigInteger.valueOf(0);

        // Calculate value of shoelace formula
        final int n = coords.size();
        int j = n - 1;
        for (int i = 0; i < n; i++)
        {
            area = area.add(BigInteger.valueOf(
                    (coords.get(j).x + coords.get(i).x) * (coords.get(j).y - coords.get(i).y)
            ));

            // j is previous vertex to i
            j = i;
        }

        // Return absolute value
        return area.compareTo(BigInteger.ZERO) >= 0;
    }
}
