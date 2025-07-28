package entity;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class BagObject {
    public int worldX, worldY;
    public BufferedImage image;
    public boolean collected = false;
    public Rectangle hitbox;

    public BagObject(int worldX, int worldY, BufferedImage image) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.image = image;
        this.hitbox = new Rectangle(worldX, worldY, 48, 48);
    }
}
