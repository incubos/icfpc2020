package icfpc2020;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PngParser {
    public static Board loadPng(final String path, int scale, int margin) throws IOException {
        final URL resource = PngParser.class.getResource("/messages/" + path);
        final BufferedImage image = ImageIO.read(resource);
//        System.out.println("Loading " + resource + " w x h: " + image.getWidth() + " x " + image.getHeight() +
//                " scale: " + scale + " margin: " + margin);
        final int width = (image.getWidth() - 2 * margin) / scale;
        final int height = (image.getHeight() - 2 * margin) / scale;
        final Board board = new Board(width, height);
//        System.out.println("Board: " + width + " x " + height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int rgb = image.getRGB(x * scale + margin, y * scale + margin);
                board.setValue(x, y, rgb == -16777216? 0 : 1);
            }
        }
        return board;
    }

    /**
     * This code loads all the png dashboards from messages into [Board] format
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        for (int m=2; m <= 10; m++) {
            final Board board = loadPng("message" + m + ".png", 4, 4);
            System.out.println(board.toString());
        }
    }
}
