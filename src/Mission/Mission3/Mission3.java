package Mission.Mission3;

import entity.BagObject;
import main.GamePanel;
import entity.Player;
import main.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Mission3 {
    GamePanel gp;
    List<BagObject> bags = new ArrayList<>();
    int totalBags = 10;
    int bagsCollected = 0;
    long startTime;
    int timeLimitSeconds = 150;
    int endX = 40 * 48;
    int endY = 32 * 48;

    public boolean active = true;

    public Mission3(GamePanel gp) {
        this.gp = gp;
        gp.missionActive = true;
        gp.player.getPlayerImage();
        gp.player.isInMission = true;

        loadBags();
        startTime = System.currentTimeMillis();

        JOptionPane.showMessageDialog(null,
                "The bus will be leaving shortly and it's getting crowded.\nCollect " + totalBags + " bags in 60 seconds.\nThen go to garage to reserve seats for your friends!",
                "Mission 3",
                JOptionPane.INFORMATION_MESSAGE);
        SoundPlayer.play("/resources/Sounds/Start.wav");

        gp.keyH.upPressed = false;
        gp.keyH.downPressed = false;
        gp.keyH.leftPressed = false;
        gp.keyH.rightPressed = false;

    }

    public void loadBags() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/bag.png"));

            //bag positions
            List<Point> validPositions = Arrays.asList(
                    new Point(27, 82),
                    new Point(36, 72),
                    new Point(38, 62),
                    new Point(15, 51),
                    new Point(61, 46),
                    new Point(84, 48),
                    new Point(95, 61),
                    new Point(85, 78),
                    new Point(34, 69),
                    new Point(16, 33),
                    new Point(36, 70),
                    new Point(38, 67)
            );

            Collections.shuffle(validPositions);

            for (int i = 0; i < totalBags; i++) {
                Point p = validPositions.get(i);
                bags.add(new BagObject(p.x * gp.tileSize, p.y * gp.tileSize, image));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update() {
        if (!active) return;
        checkBagCollection();
        checkMissionEnd();
        int elapsed = (int) ((System.currentTimeMillis() - startTime) / 1000);
        if (elapsed > timeLimitSeconds) {
            active = false;
            endMission(false);
        }
    }

    public void checkBagCollection() {
        for (BagObject bag : bags) {
            if (!bag.collected &&
                    Math.abs(gp.player.worldX - bag.worldX) < gp.tileSize &&
                    Math.abs(gp.player.worldY - bag.worldY) < gp.tileSize) {
                bag.collected = true;
                bagsCollected++;
            }
        }
    }

    public void checkMissionEnd() {
        if (bagsCollected >= totalBags &&
                Math.abs(gp.player.worldX - endX) < gp.tileSize &&
                Math.abs(gp.player.worldY - endY) < gp.tileSize) {
            active = false;
            endMission(true);
            gp.keyH.upPressed = false;
            gp.keyH.downPressed = false;
            gp.keyH.leftPressed = false;
            gp.keyH.rightPressed = false;

        }
    }

    public void draw(java.awt.Graphics2D g2) {
        for (BagObject bag : bags) {
            if (!bag.collected) {
                int screenX = bag.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = bag.worldY - gp.player.worldY + gp.player.screenY;
                g2.drawImage(bag.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }

        drawTimer(g2);

    }

    public void drawTimer(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        int timeLeft = Math.max(0, timeLimitSeconds - (int)((System.currentTimeMillis() - startTime) / 1000));
        g2.drawString("Time Left: " + timeLeft + "s", 20, 30);
    }

    public void endMission(boolean success) {
        if (success) {
            JOptionPane.showMessageDialog(null, "You collected all the bags!\nMission Complete!");
            SoundPlayer.play("/resources/Sounds/Success.wav");
        } else {
            JOptionPane.showMessageDialog(null, "Time's up!\nMission Failed.");
            SoundPlayer.play("/resources/Sounds/Fail.wav");
        }
        gp.player.isInMission = false;
        gp.missionActive = false;
    }
}
