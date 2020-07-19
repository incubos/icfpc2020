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
    private final int xoffset;
    private final int yoffset;


    public ImageRenderer(String file) {
        this.width = 20;
        this.height = 20;
        this.xoffset = width / 2;
        this.yoffset = height / 2;
        this.file = file;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
    }

    public ImageRenderer(String file, final java.util.List<Draw.Coord> coords) {
        int minx = 1000;
        int maxx = -1000;
        int miny = 1000;
        int maxy = -1000;
        for (Draw.Coord coord : coords) {
            minx = Math.min(minx, (int) coord.x);
            maxx = Math.max(maxx, (int) coord.x);
            miny = Math.min(miny, (int) coord.y);
            maxy = Math.max(maxy, (int) coord.y);
        }
        this.file = file;
        this.width = maxx - minx + 10;
        this.height = maxy - miny + 10;
        this.xoffset = width / 2;
        this.yoffset = height / 2;
        System.out.println("w,h,x,y: "+ this.width+ "," + this.height + "," + this.xoffset + "," + this.yoffset);

        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
        for (Draw.Coord coord : coords) {
            putDot(coord);
        }
        try {
            persist();
        } catch (IOException e) {
            log.error("Failed to save file: " + file, e);
        }
    }


    public void putDot(final Draw.Coord coord) {
        if (Math.abs(coord.x) > width / 2 || Math.abs(coord.y) > height / 2) {
            log.error("Coord is outside canvas, x={}, y={}", coord.x, coord.y);
        }
        bufferedImage.setRGB((int) coord.x + xoffset,
                             (int) coord.y + yoffset,
                             255);
    }

    public void persist() throws IOException {
        ImageIO.write(bufferedImage, "PNG", new File(file));
    }
}
