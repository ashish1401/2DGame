package com.javaproject1;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.black;
    /*
    JPanel is a simplest lightweight container class that is a part of the package java.swing.
    It can group or store a set of components together, mainly for creating a user interface.
    It is similar to the panel in Abstract Window Toolkit (AWT). JPanel does not contain border,
    title bar or menu bar. Even though several layout managers exist, FlowLayout is the default layout manager of JPanel
    and it inherits the class JComponents. If a component has to be attached to an application, JPanel provides space.
     */
public class GamePanel extends JPanel implements Runnable {
    //WORKS AS THE GAME SCREEN
    // SCREEN SETTINGS
    final int originalTilesSize = 16; // 16x6 tile for character
    final int scale=3;
    public final int tileSize= originalTilesSize*scale; // 48X48 tile - scaling the character's size to make it visible
    public final int maxScreenCol=16;// in terms of num of tiles
    public final int maxScreenRow=12;// in terms of num of tiles
    public final int screenWidth= tileSize*maxScreenCol;//16*48=768px
    public final int screenHeight=tileSize*maxScreenRow;//12*48=576px
    //FPS
    int FPS =60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH=new KeyHandler(); //creating a new key handler
        //FOP - 2
    Thread gameThread;
    //to keep running a program:overrides to run function
    Player player=new Player(this , keyH);
    //Set player's default location:
//    int playerX=100;
//    int playerY=100;
    int playerSpeed=4;
    //creating a constructor:
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        //sets the size of our panel:
        /*
        Hence on writing: GamePanel a=new GamePanel();
        a panel of height 576px and width 768px will be created
         */
        this.setBackground(black);
        this.setDoubleBuffered(true);//helps in improving games rendering ability
        this.addKeyListener(keyH);
        this.setFocusable(true); //with this game panel can be"focused" to recieve key input
    }

    public void startGameThread(){
        gameThread=new Thread(this);//TARGETS THE GAME PANEL
        gameThread.start(); //CALLS THE RUN FUNCTION
    }


    @Override
    public void run() {
        //now we'll create a game loop which is core of the game
        double drawInterval=1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread.isAlive()){    //while gamethread!=null
            try {
                double remainingTime = nextDrawTime-System.nanoTime();
                remainingTime=remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime=nextDrawTime+drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //In this loop we'll:
            // 1.UPDATE: update information such as character positions
                update();
            // 2.DRAW: draw the screen with updated information
               repaint();
            // If the fps of the game is 60 we do steps 1,2 60 times in
            // a second
            // Time after update and repainting has been done but
            //moving to next interval has some time left
        }
    }
       //FOP - 3
      public void update(){
        //changing position
        player.update();
    }
    //how ever this wont work yet , as we need to make a time interval
    //in which update and repaint work otherwise on pressing w once the character will move around million
    //pixels

//    1
    public void paintComponent(Graphics g){
        super.paintComponent(g);//super means parent clss of the above class ie GamePanel
        //not a self created function but a standard method in JPanel
        Graphics2D g2=(Graphics2D)g; //typecasting g into 2DGraphics to be able use its properties
        tileM.draw(g2);
        player.draw(g2);
        //will draw rectangle on screen with dimensions of above mentioned TileSize
        g2.dispose(); //disposes of this graphics context and releases any system resources that it is using
        //not necessary but good practice
        //now to update its position we need key inputs so we'll  create a new clss
    }
}


