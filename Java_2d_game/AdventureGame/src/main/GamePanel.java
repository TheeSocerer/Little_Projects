package main;

import main.KeyHandler;
import user.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //screen settings

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48X48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize* maxScreenCol;// 768 pixels
    final int screenHeight = tileSize* maxScreenRow;//576 pixels
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    //fps = 60
    private int FPS = 60;

    //set players defaulst psoition
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
//    @Override
//    public void run() {
//        double drawInterval = (double) 1000000000 /FPS; //0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null){
//            long currentTime = System.nanoTime();
//
//            //1 update; update the position
//            update();
//            //2 draw; draw the screen with update information
//            repaint();
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;// convert to milliseconds
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long)remainingTime);
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
@Override
public void run() {
    double drawInterval = (double) 1000000000 /FPS; //0.01666 seconds
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    long timer = 0;
    long drawcount = 0;

    while(gameThread != null){
        currentTime = System.nanoTime();

        delta += (currentTime-lastTime)/drawInterval;
        timer += (currentTime-lastTime);
        lastTime = currentTime;

        if(delta>=1){
            //1 update; update the position
            update();
            //2 draw; draw the screen with update information
            repaint();
            delta--;
            drawcount++;
        }
        if(timer>= 1000000000){
            System.out.println("FSP: "+ drawcount);
            timer = 0;
            drawcount = 0;
        }
    }
}

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
    }
}
