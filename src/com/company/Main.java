package com.company;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        JFrame frame = new JFrame("Zombie");
        DrawPanel panel = new DrawPanel(Main.class.getResource("/resources/cementary.jpg"), ZombieFactory.INSTANCE);
        frame.setContentPane(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
               @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    System.exit(0);
                }
            });




    }}

