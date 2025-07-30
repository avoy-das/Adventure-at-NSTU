package Mission.Mission5;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class BusObject {
    public int worldX, worldY;
    public BufferedImage image;
    public Rectangle hitbox;
    public boolean reachedStop = false;

    public BusObject(int worldX, int worldY, BufferedImage image) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.image = image;
        this.hitbox = new Rectangle(worldX, worldY, 48, 48); // tileSize
    }
//    public void moveRight(int speed) {
//        worldX += speed;
//        hitbox.x = worldX;
//    }
}
