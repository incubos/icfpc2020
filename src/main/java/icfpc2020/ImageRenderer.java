package icfpc2020;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageRenderer {

    private final BufferedImage bufferedImage;
    private final String file;


    public ImageRenderer(String file) {
        this.file = file;
        this.bufferedImage = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(Integer.MAX_VALUE));
        graphics.fillRect(0,0,100,100);
        graphics.dispose();
    }

    public void putDot(final Draw.Coord coord) {
        bufferedImage.setRGB((int) coord.x,
                             (int) coord.y,
                             0);
    }

    public void persist() throws IOException {
        ImageIO.write(bufferedImage, "PNG", new File(file));
    }
}
