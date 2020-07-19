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
    private final int minx;
    private final int miny;


    public ImageRenderer(String file) {
        this.width = 1000;
        this.height = 1000;
        this.minx = - width / 2;
        this.miny = - height / 2;
        this.file = file;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
    }

    public ImageRenderer(String file, final java.util.List<Draw.Coord> coords) {
        // 0, 0 point should be explicitly included!
        int minx = 0;
        int maxx = 0;
        int miny = 0;
        int maxy = 0;
        for (Draw.Coord coord : coords) {
            minx = Math.min(minx, (int) coord.x);
            maxx = Math.max(maxx, (int) coord.x);
            miny = Math.min(miny, (int) coord.y);
            maxy = Math.max(maxy, (int) coord.y);
        }
        this.file = file;
        this.width = maxx - minx + 1;
        this.height = maxy - miny + 1;
        this.minx = minx;
        this.miny = miny;

        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(new Color(100000));
        if (width > 50) {
            for (int x = minx; x < maxx; x += this.width / 10) {
                final int xgrid = x - minx;
                graphics.drawLine(xgrid, 0, xgrid, height);
            }
        } else {
            graphics.drawLine(0, 0, 0, height);
        }
        if (width > 50) {
            for (int y = miny; y < maxy; y += this.height / 10) {
                final int ygrid = y - this.miny;
                graphics.drawLine(0, ygrid, width, ygrid);
            }
        } else {
            graphics.drawLine(0, 0, width, 0);
        }
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
        final int x = (int) coord.x - minx;
        final int y = (int) coord.y - miny;
        if (x < 0 || x > width || y < 0 || y > height) {
            log.error("Coord is outside canvas, x={}, y={}", coord.x, coord.y);
        }
        try {
            bufferedImage.setRGB(x, y, 255);
        } catch (Exception e) {
            log.error("exception while writing to buffered image, x={} y={}, coord=<{}>", x, y, coord);
        }
    }

    public void persist() throws IOException {
//        log.info("persisting image=<{}>, width=<{}>, height=<{}>, xoffset=<{}>, yoffset=<{}>",
//                 file, width, height, minx, miny);
        bufferedImage.setRGB(-minx, -miny, 0xFFFFFF);
        ImageIO.write(bufferedImage, "PNG", new File(file));
    }
}
