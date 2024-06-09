package user;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_down_1.png")));
            down2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/boy_right_2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if( keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed){

            if(keyH.upPressed){
                direction ="up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNumber==1){
                    spriteNumber =2;
                } else if (spriteNumber==2) {
                    spriteNumber =1;
                }
                spriteCounter =0;
            }
        }
    }
    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
//        g2.dispose();
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNumber ==1){
                    image = up2;
                }else{
                    image = up1;
                }
                break;
            case "down":
                if (spriteNumber ==1){
                    image = down2;
                }else{
                    image = down1;
                }
                break;
            case "left":
                if (spriteNumber ==1){
                    image = left2;
                }else {
                    image = left1;
                }
                break;
            case "right":
                if(spriteNumber==1){
                    image = right2;
                }else{
                    image = right1;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }

}
