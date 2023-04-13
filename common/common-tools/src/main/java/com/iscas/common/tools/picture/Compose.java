package com.iscas.common.tools.picture;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * @author admin
 */
@SuppressWarnings("unused")
public class Compose {

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static void mergeImage(String bigPath, String smallPath, String x, String y) {

        try {
            BufferedImage small;
            BufferedImage big = ImageIO.read(new File(bigPath));
            if (smallPath.contains("http")) {

                URL url = new URL(smallPath);
                small = ImageIO.read(url);
            } else {
                small = ImageIO.read(new File(smallPath));
            }

            Graphics2D g = big.createGraphics();

            float fx = Float.parseFloat(x);
            float fy = Float.parseFloat(y);
            int xI = (int) fx;
            int yI = (int) fy;
            g.drawImage(small, xI, yI, small.getWidth(), small.getHeight(), null);
            g.dispose();
            ImageIO.write(big, "png", new File(smallPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}