package icfpc2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageRenderer {
    private static final Logger log = LoggerFactory.getLogger(ImageRenderer.class);
    private final BufferedImage bufferedImage;
    private final String file;
    private final int width;
    private final int height;


    public ImageRenderer(String file) {
        this.file = file;
        this.width = 1024;
        this.height = 1024;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
    }

    public void putDot(final Draw.Coord coord) {
        if (coord.x > width || coord.y > height) {
            log.error("Coord is outside canvas, x={}, y={}", coord.x, coord.y);
        }
        bufferedImage.setRGB((int) coord.x,
                             (int) coord.y,
                             Integer.MAX_VALUE);
    }

    public void persist() throws IOException {
        ImageIO.write(bufferedImage, "PNG", new File(file));
    }
}
