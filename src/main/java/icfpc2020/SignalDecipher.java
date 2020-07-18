package icfpc2020;

public class SignalDecipher {
    public static Board decipherSignal(String signal) {
        final int length = signal.length();
        System.out.println("Signal " + signal);
        System.out.println("Length " + length);
        // https://icfpcontest2020.github.io/#/post/2050
        // This post has messages in 3x14 format
        for (int w = 2; w <= length; w++) {
            if (length % w == 0) {
                final int h = length / w;
                System.out.println("Board "  + w + " x " + h);
                final Board result = new Board(w, h);
                for (int x = 0; x < w; x++) {
                    for (int y = 0; y < h; y++) {
                        result.setValue(x, y, signal.charAt(x * h + y) == '1' ? 1 : 0);
                    }
                }
                System.out.println("Board");
                System.out.println(result.toString());
                System.out.println("Content");
                System.out.println(BoardDecipher.dumpCommands(BoardDecipher.decipher(result)));
                System.out.println();
            }
        }
        System.out.println();
        return null;
    }

    public static void main(String[] args) {
        // Messages from https://icfpcontest2020.github.io/#/post/2050
        decipherSignal("110110000111011111100001001111110101000000");
        decipherSignal("110110000111011111100001001111110100110000");

        // Our messages
        decipherSignal("1101000");
        decipherSignal("11011000011101000");
        decipherSignal("11011000011101000");
        decipherSignal("11111111111111111111111111111111");
    }
}
