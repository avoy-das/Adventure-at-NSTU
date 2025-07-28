package tile;
import java.awt.image.BufferedImage;
public class MapObject {
    private int x, y;
    private BufferedImage image;
    public MapObject(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;

    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public BufferedImage getImage() {
        return image;
    }

}
