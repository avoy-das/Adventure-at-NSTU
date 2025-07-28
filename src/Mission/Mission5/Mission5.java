package Mission.Mission5;

import main.GamePanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mission5 {
    GamePanel gp;
    public boolean active = true;
    BusObject bus;
    int stopX = 38 * 48;
    int stopY = 94 * 48;

    int busSpeed = 2;
    long lastMoveTime;
    int moveInterval = 100;

    int[][] path = {
            {51, 34},{52, 34},{53, 34},{54, 34},{55, 34},{56, 34},{57, 34},
            {57,35},{57,36},{57,37},{57,38},{57,39},{57,40},{57,41},{57,42},{57,43},{57,44},{57,45},{57,46},{57,47},{57,48},{57,49},{57,50},{57,51},{57,52},{57,53},{57,54},{57,55},{57,56},{57,57},{57,58},{57,59},{57,60},{57,61},{57,62},{57,63},{57,64},{57,65},{57,66},{57,67},{57,68},{57,69},{57,70},{57,71},{57,72},{57,73},{57,74},{57,75},{57,76},{57,77},{57,78},{57,79},{57,80},{57,81},
            {57,81},{56,81},{55,81},{54,81},{53,81},{52,81},{51,81},{50,81},{49,81},{48,81},{47,81},{46,81},{45,81},{44,81},{43,81},{42,81},{41,81},{40,81},{39,81},
            {39,82},{39,82},{40,82},{40,83},{40,84},{41,84},{42,84},{42,85},{42,86},{41,87},{38,87},{38,88},{38,89},{38,90},{38,91},{38,92},{38,93},
            {38, 94}
    };
    int currentPathIndex = 0;
    long missionStartTime = System.currentTimeMillis();

    public Mission5(GamePanel gp) {
        this.gp = gp;
        gp.missionActive = true;
        gp.player.isInMission = true;

        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/resources/RedBus.png"));
            int startCol = 51;
            int startRow = 34;
            bus = new BusObject(startCol * gp.tileSize, startRow * gp.tileSize, img);

        } catch (Exception e) {
            e.printStackTrace();
        }

        lastMoveTime = System.currentTimeMillis();

        JOptionPane.showMessageDialog(null,
                "Hurry! Catch the bus before it reaches the stop!",
                "Mission 5", JOptionPane.INFORMATION_MESSAGE);

        gp.keyH.upPressed = false;
        gp.keyH.downPressed = false;
        gp.keyH.leftPressed = false;
        gp.keyH.rightPressed = false;

    }

    public void update() {
        if (!active) return;

        long now = System.currentTimeMillis();
        if (now - lastMoveTime > moveInterval) {
            moveBusAlongPath();
            lastMoveTime = now;
        }

        int elapsedSeconds = (int)((System.currentTimeMillis() - missionStartTime) / 1000);
        busSpeed = 20 + elapsedSeconds / 10;

        checkConditions();
    }

    public void moveBusAlongPath() {
        if (currentPathIndex >= path.length) return;

        int targetX = path[currentPathIndex][0] * gp.tileSize;
        int targetY = path[currentPathIndex][1] * gp.tileSize;

        if (Math.abs(bus.worldX - targetX) <= busSpeed &&
                Math.abs(bus.worldY - targetY) <= busSpeed) {

            bus.worldX = targetX;
            bus.worldY = targetY;
            currentPathIndex++;
        } else {

            if (bus.worldX < targetX) bus.worldX += busSpeed;
            else if (bus.worldX > targetX) bus.worldX -= busSpeed;

            if (bus.worldY < targetY) bus.worldY += busSpeed;
            else if (bus.worldY > targetY) bus.worldY -= busSpeed;
        }
    }


    public void checkConditions() {

        if (!bus.reachedStop &&
                Math.abs(gp.player.worldX - bus.worldX) < gp.tileSize &&
                Math.abs(gp.player.worldY - bus.worldY) < gp.tileSize) {
            missionSuccess();
            return;
        }

        // Bus reaches stop
        if (!bus.reachedStop &&
                bus.worldX == stopX &&
                bus.worldY == stopY) {
            bus.reachedStop = true;
            missionFail();
        }

    }

    public void missionSuccess() {
        active = false;
        JOptionPane.showMessageDialog(null, "You caught the bus in time!\nMission Complete.");
        gp.keyH.upPressed = false;
        gp.keyH.downPressed = false;
        gp.keyH.leftPressed = false;
        gp.keyH.rightPressed = false;
        gp.player.isInMission = false;
        gp.missionActive = false;
    }

    public void missionFail() {
        active = false;
        JOptionPane.showMessageDialog(null, "You missed the bus!\nMission Failed.");
        gp.keyH.upPressed = false;
        gp.keyH.downPressed = false;
        gp.keyH.leftPressed = false;
        gp.keyH.rightPressed = false;
        gp.player.isInMission = false;
        gp.missionActive = false;
    }
    public void draw(Graphics2D g2) {
        if (bus != null) {
            int screenX = bus.worldX - gp.player.worldX + gp.player.screenX;
            int screenY = bus.worldY - gp.player.worldY + gp.player.screenY;
            g2.drawImage(bus.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
