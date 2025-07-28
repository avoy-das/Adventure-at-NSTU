package Mission.Mission6;

import java.awt.*;

public class Bullet {
    public int x, y;
    public int speed = 6;
    public String direction;
    public boolean active = true;
    public int size = 8;

    public Bullet(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void update() {
        switch (direction) {
            case "right": x += speed; break;
            case "left": x -= speed; break;
            case "up": y -= speed; break;
            case "down": y += speed; break;
        }

        // simple boundary check
        if (x < 0 || x > 2000 || y < 0 || y > 2000) {
            active = false;
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillOval(x, y, size, size);
    }
}
