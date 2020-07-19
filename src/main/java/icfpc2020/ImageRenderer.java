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
        this.xoffset = -minx + 5;
        this.yoffset = -miny + 5;
//        System.out.println("w,h,x,y: "+ this.width+ "," + this.height + "," + this.xoffset + "," + this.yoffset);

        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(new Color(100000));
        if (maxx - minx > 50) {
            for (int i = minx; i < maxx; i += (maxx - minx) / 10) {
                graphics.drawLine(i + xoffset, miny + yoffset, i + xoffset, maxy + yoffset);
            }
        } else {
            graphics.drawLine(xoffset, miny + yoffset, xoffset, maxy + yoffset);
        }
        if (maxy - miny > 50) {
            for (int i = miny; i < maxy; i += (maxy - miny) / 10) {
                graphics.drawLine(minx + xoffset, i + yoffset, maxx + xoffset, i + yoffset);
            }
        } else {
            graphics.drawLine(minx + xoffset, yoffset, maxx + xoffset, yoffset);
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
        try {
            final int x = (int) coord.x + xoffset;
            final int y = (int) coord.y + yoffset;
            if (x < 0 || x > width || y < 0 || y > height) {
                log.error("Coord is outside canvas, x={}, y={}", coord.x, coord.y);
            }
            bufferedImage.setRGB(x, y, 255);
        } catch (Exception e) {
            log.error("exception while writing to buffered image, coord=<{}>", coord);
        }
    }

    public void persist() throws IOException {
        log.info("persisting image=<{}>, width=<{}>, height=<{}>, xoffset=<{}>, yoffset=<{}>",
                 file, width, height, xoffset, yoffset);
        bufferedImage.setRGB(xoffset, yoffset, 0xFFFFFF);
        ImageIO.write(bufferedImage, "PNG", new File(file));
    }
}
