package icfpc2020.galaxy;

import java.math.BigInteger;

public class ClickerRoundabout implements Clicker{
    // Go and click by square
    int squareX = 0;
    private Vect lastClick;
    int[][] deltas = new int[][] {
            new int[] {0, 1},
            new int[] {-1, 0},
            new int[] {0, -1},
            new int[] {1, 0},
            new int[] {0, 1}
    };
    // Current click direction
    int dState = 0;

    public Vect nextClick() {
        // First step
        if (lastClick == null) {
            // Go ending round
            lastClick = new Vect(0, -1);
            squareX = 0;
            dState = 4;
            return new Vect(0, 0);
        }
        // Go 0, 1 ending the round
        if ((dState == 3 || dState == 4) &&
                lastClick.X.equals(BigInteger.valueOf(squareX)) &&
                lastClick.Y.equals(BigInteger.valueOf(-1))) {
            squareX += 1;
            lastClick = new Vect(squareX, 0);
            dState = 0;
            return lastClick;
        }
        int[] dxy = deltas[dState];
        Vect newClick = lastClick.d(dxy[0], dxy[1]);
        if (newClick.X.abs().compareTo(BigInteger.valueOf(squareX)) <= 0 &&
                newClick.Y.abs().compareTo(BigInteger.valueOf(squareX)) <= 0) {
            lastClick = newClick;
            return lastClick;
        } else {
            // Change direction
            dState++;
            dxy = deltas[dState];
            lastClick = lastClick.d(dxy[0], dxy[1]);
            return lastClick;
        }
    }

}
