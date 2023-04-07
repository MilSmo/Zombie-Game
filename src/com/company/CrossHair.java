package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class CrossHair implements MouseMotionListener, MouseListener, CrossHairListener {

    DrawPanel parent;

    List<CrossHairListener> listeners;


    CrossHair(DrawPanel parent){
        this.parent = parent;
        parent.addMouseMotionListener(this);
        parent.addMouseListener(this);
        listeners = new ArrayList<>();


    }




    /* x, y to współrzedne celownika
       activated - flaga jest ustawiana po oddaniu strzału (naciśnięciu przyciku myszy)
    */
    int x;
    int y;
    boolean activated = false;
    void draw(Graphics2D g){
        if (parent.getMousePosition() != null) {
            x = parent.getMousePosition().x;
            y = parent.getMousePosition().y;
        }
        else {
            x = 0;
            y = 0;
        }
        Graphics2D g2d = (Graphics2D)g;
        if (activated) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.GREEN);
        }
        g2d.drawLine(x-20,y,x+20,y);
        g2d.drawLine(x,y-20,x,y+20);


    }

    void addCrossHairListener(CrossHairListener e){
        listeners.add(e);


    }
    void notifyListeners(){
        for(var e:listeners)
            e.onShotsFired(x,y);

    }

    @Override
    public void mouseClicked(MouseEvent e) {



    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        activated = true;
        parent.repaint();

        Timer timer = new Timer("Timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activated = false;
                parent.repaint();
            }
        }, 300);
        notifyListeners();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {



    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        parent.repaint();
    }

    @Override
    public void onShotsFired(int x, int y) {





    }
}

