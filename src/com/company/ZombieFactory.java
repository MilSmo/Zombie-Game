package com.company;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ZombieFactory implements SpriteFactory {
    INSTANCE;

    private BufferedImage tape;

    private ZombieFactory() {
        try {
            tape = ImageIO.read(getClass().getResource("/resources/walkingdead.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite newSprite(int x, int y) throws IOException {
        double scale = Math.random() * (2.0 - 0.2) + 0.2;
        return new Zombie(x, y, scale, tape);
    }
}
