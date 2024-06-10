package main;

import user.Entity;

public class CollisionChecker {
    public GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftworldX = entity.worldX + entity.solidArea.x;
        int entityRigtWorldX = entity.worldX+ entity.solidArea.x+ entity.solidArea.width;

        int entityTopworldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY+ entity.solidArea.y+ entity.solidArea.height;

        int entityleftCol = entityLeftworldX/gp.tileSize;
        int entityRightCol = entityRigtWorldX/gp.tileSize;
        int entityTopRow = entityTopworldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopworldY+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityleftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityleftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityleftCol = (entityLeftworldX-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityleftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityleftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRigtWorldX-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
