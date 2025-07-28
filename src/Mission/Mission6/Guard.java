package Mission.Mission6;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Guard {
    public int x, y;
    public int speed = 2;
    public String direction;
    public BufferedImage image;
    public long lastShotTime = 0;
    public int shootInterval = 2000;

    public int patrolDistance = 96; // in pixels (e.g., 2 tiles)
    private int startX, startY;
    private boolean movingForward = true;

    public Guard(int x, int y, String direction, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.direction = direction;
        this.image = image;
    }

    public boolean readyToShoot() {
        long now = System.currentTimeMillis();
        return (now - lastShotTime >= shootInterval);
    }

    public void resetShotTimer() {
        lastShotTime = System.currentTimeMillis();
    }

    public void update() {
        switch (direction) {
            case "right":
            case "left":
                if (movingForward) {
                    x += speed;
                    if (x >= startX + patrolDistance) {
                        movingForward = false;
                        direction = "left";
                    }
                } else {
                    x -= speed;
                    if (x <= startX) {
                        movingForward = true;
                        direction = "right";
                    }
                }
                break;

            case "down":
            case "up":
                if (movingForward) {
                    y += speed;
                    if (y >= startY + patrolDistance) {
                        movingForward = false;
                        direction = "up";
                    }
                } else {
                    y -= speed;
                    if (y <= startY) {
                        movingForward = true;
                        direction = "down";
                    }
                }
                break;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
    }
}
