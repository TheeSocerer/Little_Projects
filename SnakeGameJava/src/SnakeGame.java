import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    Tile snakeHead;
    ArrayList<Tile> snakeBody;
    Tile food;

    Random random;

    //game logic
    Timer gameloop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;
    public SnakeGame(int boardHeight, int boardWidth){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);

        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5,5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10,10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;



        gameloop = new Timer(100,this);
        gameloop.start();
    }
    public void move(){
        if( collisions(snakeHead, food)){
            snakeBody.add(new Tile(food.x,food.y));
            placeFood();

        }
        //Snake Body
        for( int i = snakeBody.size()-1; i>=0;i--){
            Tile snakePart = snakeBody.get(i);
            if( i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }else {
                Tile prevSnakePArt = snakeBody.get(i-1);
                snakePart.x = prevSnakePArt.x;
                snakePart.y = prevSnakePArt.y;
            }
        }

        //Snake Head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over condition
        for( int i = 0; i<snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            if( collisions(snakeHead , snakePart)){
                gameOver = true;
            }
        }

        if ( snakeHead.x*tileSize<=0 || snakeHead.x*tileSize>=boardWidth ||
        snakeHead.y*tileSize<=0 || snakeHead.y*tileSize>=boardHeight){
            gameOver = true;
        }

    }
    public boolean collisions(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        move();
        repaint();
        if (gameOver){
            gameloop.stop();
        }
    }



    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP && velocityY !=1) {
            velocityX = 0;
            velocityY = -1;
        }else if( key.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }else if(key.getKeyCode()==KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }else if( key.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        for(int i = 0;i<boardWidth/tileSize;i++){
            //(x1,y1,x2,y2)
            g.drawLine(i*tileSize,0,i*tileSize, boardHeight);
            g.drawLine(0,i*tileSize,boardWidth, i*tileSize);

        }
        //food

        g.setColor(Color.RED);
        g.fillRect(food.x*tileSize,food.y*tileSize, tileSize, tileSize);



        //snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize,tileSize,tileSize);

        //Snake body
        for( int i = 0; i< snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
        }

    }

    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }
}
