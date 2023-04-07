package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;


public class DrawPanel  extends JPanel implements CrossHairListener{
    BufferedImage background;
    Zombie zombie;
    List<Sprite> sprites = new ArrayList<>();
    SpriteFactory factory;
    CrossHair crossHair;
    ScoreBoard scoreBoard = new ScoreBoard(10,30);


    public void update(){
        sprites.removeIf(sprite -> !sprite.isVisible());
    }

    DrawPanel(URL backgroundImagageURL, SpriteFactory factory){
        try {
            background = ImageIO.read(backgroundImagageURL);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        this.factory=factory;
        this.getMousePosition();
        AnimationThread thread = new AnimationThread();
        thread.start();
        crossHair = new CrossHair(this);
        crossHair.addCrossHairListener(this);
        addMouseListener(crossHair);
        addMouseMotionListener(crossHair);





    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(background,0,0,getWidth(),getHeight(),this);

        for(Sprite s: sprites){
            s.draw(g2d,this);

        }
        crossHair.draw(g2d);
        scoreBoard.draw(g2d);



    }


    @Override
    public void onShotsFired(int x, int y) {
        for (int i = sprites.size() - 1; i >= 0; i--) {
            Sprite sprite = sprites.get(i);
            if (sprite.isHit(x, y)) {
                sprites.remove(sprite);
                update();
                scoreBoard.shot();
                break;
            }
        }
    }
    static class SpriteComparator implements Comparator<Sprite> {
        @Override
        public int compare(Sprite o1, Sprite o2) {
            return o1.isCloser(o2) ? -1 : 1;
        }
    }


    class AnimationThread extends Thread{

        public void run(){


            for(int i=0;;i++) {

                for (Sprite s : sprites) {
                    s.next();
                }
                synchronized (sprites)
                {
                    update();
                }

                repaint();
                if(i%30 ==0){
                    try {
                        synchronized(sprites) {
                            Sprite newZombie = factory.newSprite(getWidth(), (int) (Math.random() * getHeight()));
                            sprites.add(newZombie);
                            Collections.sort(sprites, new SpriteComparator());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    sleep(1000  / 30);  // 30 klatek na sekundę
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }}


    }}