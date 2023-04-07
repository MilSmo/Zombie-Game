package com.company;

import java.awt.*;

public class ScoreBoard {
    private int score = 0;
    private int x, y;
    private Font font;

    public ScoreBoard(int x, int y) {
        this.x = x;
        this.y = y;
        this.font = new Font("Arial", Font.BOLD, 20);
    }

    public void shot() {
        score++;

    }

    public void draw(Graphics g) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, x, y);
    }
}

