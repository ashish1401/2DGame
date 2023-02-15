package com.javaproject1;

import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        /* JFrame is a top-level container that provides a
        window on the screen. A frame is actually a base
        window on which other components rely, namely the
        menu bar, panels, labels, text fields, buttons, etc.
        Almost
        JFrame class has many constructors that are used to
        create a new JFrame.
        You can create a JFrame using these methods:
        JFrame(): This helps in creating a frame
        which is invisible.
        JFrame(String Title): Helps in creating a frame with
        a title.
        JFrame(GraphicsConfiguration gc): Creates a frame with blank title
        and the graphics configuration of screen.*/
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*This Command lets the window close properly when user clicks the
        close ("x") button */
        window.setResizable(false); //prevent window from being resized
        window.setTitle("2D Adventure");

        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);
        window.pack();

        gamePanel.startGameThread();















































        window.setLocationRelativeTo(null);//no specific location=window will be displayed at the center
        window.setVisible(true);



    }
}
