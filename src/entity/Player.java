package entity;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
public class Player extends Entity {
    public GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public CollisionChecker cChecker;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 ;
        screenY = gp.screenHeight/2;
        solidArea = new Rectangle(8,16,32,32);
        setDefaultValues();
        getPlayerImage();
    }

    public void setPosition(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 46 ;
        worldY = gp.tileSize * 94 ;
        speed = 5;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/boy/right_2.png")));
        } catch (IOException e) {
            System.out.println("Image not loading");
        }
    }

    public boolean isInMission = false;
    public void update() {
        if ( !gp.missionActive || isInMission  ) {

            if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {

                if (keyH.upPressed) {
                    direction = "up";
                }
                if (keyH.downPressed) {
                    direction = "down";
                }
                if (keyH.leftPressed) {
                    direction = "left";
                }
                if (keyH.rightPressed) {
                    direction = "right";
                }

                collisionOn = false;

                if (cChecker != null) {
                    cChecker.checkTiles(this);
                }

                if (!collisionOn)
                {
                    switch (direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;}
                }
                else
                {
                    System.out.println("Collision");
                }

                spriteCounter++;
                if (spriteCounter > 5) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if (!isInMission) {
            gp.missionManager.checkTrigger(worldX, worldY);
            }
        }
        System.out.println("WorldX: " + worldX / 48 + ",WorldY: " + worldY / 48);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction){
            case "up":
                if (spriteNum==1){
                    image = up1;
                }
                if (spriteNum==2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum==1){
                    image = down1;
                }
                if (spriteNum==2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum==1){
                    image = left1;
                }
                if (spriteNum==2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum==1){
                    image = right1;
                }
                if (spriteNum==2){
                    image = right2;
                }
                break;
        }

        int drawX;
        int drawY;

        if (isInMission) {
            drawX = worldX;
            drawY = worldY;
        } else {
            drawX = screenX;
            drawY = screenY;
        }
        g2.drawImage(image,drawX,drawY,gp.tileSize,gp.tileSize,null);
    }

}
