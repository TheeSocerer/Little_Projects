import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JPanel {
    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    Tile snakeHead;

    Tile food;
    public SnakeGame(int boardHeight, int boardWidth){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);

        snakeHead = new Tile(5,5);

        food = new Tile(10,10);
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

    }
}
