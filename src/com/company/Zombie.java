package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombie implements Sprite {
    BufferedImage tape;
    int x = 500;
    int y = 500;
    double scale = 1;
    int index = 0;  // numer wyświetlanego obrazka
    int HEIGHT, WIDTH;


    Zombie(int x, int y, double scale, BufferedImage tape) throws IOException {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.tape = ImageIO.read(getClass().getResource("/resources/walkingdead.png"));
        this.HEIGHT = this.tape.getHeight();
        this.WIDTH = this.tape.getWidth() / 10;
    }


    /**
     * Pobierz odpowiedni podobraz klatki (odpowiadającej wartości zmiennej index)
     * i wyświetl go w miejscu o współrzędnych (x,y)
     *
     * @param g
     * @param parent
     */

    public void draw(Graphics g, JPanel parent) {
        if (x >= 0 && x + WIDTH * scale < parent.getWidth()) {
            Image img = tape.getSubimage(index * WIDTH, 0, WIDTH, HEIGHT);
            g.drawImage(img, x, y - (int) (HEIGHT * scale) / 2, (int) (WIDTH * scale), (int) (HEIGHT * scale), parent);
        }
    }

    /**
     * Zmień stan - przejdź do kolejnej klatki
     */

    public void next() {
        x -= 20 * scale;
        index = (index + 1) % 10;
    }

    public boolean isVisible() {
        int width = (int) (WIDTH * scale);
        return x + scale * WIDTH > 0;
    }

    public boolean isHit(int x, int y) {
        int width = (int) (WIDTH * scale);
        int height = (int) (HEIGHT * scale);
        return x >= this.x && x <= this.x + width && y >= this.y - height / 2 && y <= this.y + height / 2;

    }

    public boolean isCloser(Sprite other) {
        if (other instanceof Zombie z) {
            return scale > z.scale;
        }
        return false;
    }
}