package com.transround.nativeradmin.swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by szeibert on 2014.11.24..
 */
public class ImagePanel extends JPanel {

    private Image image;

    public ImagePanel(String imagePath) {
        setImage(imagePath);
    }

    public ImagePanel(Image image) {
        setImage(image);
    }

    public ImagePanel(InputStream inputStream) {
        setImage(inputStream);
    }

    public void setImage(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        invalidate();
    }

    public void setImage(InputStream inputStream) {
        try {
            image = ImageIO.read(inputStream);
            setPreferredSize(new Dimension(((BufferedImage)image).getWidth(), ((BufferedImage)image).getHeight()));
            invalidate();
        } catch(IOException e) {

        }
    }

    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            setPreferredSize(new Dimension(((BufferedImage)image).getWidth(), ((BufferedImage)image).getHeight()));
            invalidate();
        } catch (IOException ex) {

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}