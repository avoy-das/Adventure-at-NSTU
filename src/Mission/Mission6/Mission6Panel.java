package Mission.Mission6;
import main.GamePanel;
import main.KeyHandler;
import entity.Player;
import tile.TileManager;
import tile.MapObject;
import main.CollisionChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mission6Panel extends JPanel implements Runnable {
    int tileSize;
    int maxWorldCol = 25;
    int maxWorldRow = 18;

    GamePanel gp;
    Player missionPlayer;
    TileManager tileManager;
    KeyHandler keyH;
    CollisionChecker collisionChecker;

    ArrayList<Guard> guards = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    ArrayList<MapObject> missionObjects = new ArrayList<>();
    BufferedImage guardImage;

    Thread gameThread;

    int timeLimitSeconds = 60;
    long startTime;
    boolean missionFailed = false;
    boolean missionEnded = false;

    int goalX = 23 * 48;
    int goalY = 14 * 48;

    public Mission6Panel(GamePanel gp) {
        this.gp = gp;
        this.tileSize = gp.tileSize;
        this.keyH = gp.keyH;

        setPreferredSize(new Dimension(tileSize * maxWorldCol, tileSize * maxWorldRow));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        missionPlayer = new Player(gp, keyH);
        missionPlayer.setPosition(tileSize, tileSize);
        missionPlayer.isInMission = true;

        tileManager = new TileManager(gp, tileSize, maxWorldCol, maxWorldRow);
        tileManager.loadMap("/resources/maps/mission6map.txt");

        collisionChecker = new CollisionChecker(tileManager);
        missionPlayer.cChecker = collisionChecker;

        try {
            guardImage = ImageIO.read(getClass().getResourceAsStream("/resources/guard.png"));
            guards.add(new Guard(10 * tileSize, 5 * tileSize, "right", guardImage));
            guards.add(new Guard(15 * tileSize, 8 * tileSize, "left", guardImage));
            guards.add(new Guard(20 * tileSize, 10 * tileSize, "down", guardImage));
            guards.add(new Guard(3  * tileSize, 10 * tileSize, "up", guardImage));
            guards.add(new Guard(10 * tileSize, 12 * tileSize, "left", guardImage));
            guards.add(new Guard(13 * tileSize, 2 * tileSize, "down", guardImage));
            guards.add(new Guard(16 * tileSize, 14 * tileSize, "left", guardImage));


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            missionObjects.add(new MapObject(5, 2, ImageIO.read(getClass().getResourceAsStream("/resources/location.png"))));
            missionObjects.add(new MapObject(23, 14, ImageIO.read(getClass().getResourceAsStream("/resources/Malek.png"))));
            missionObjects.add(new MapObject(1, 1, ImageIO.read(getClass().getResourceAsStream("/resources/Vista.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }


        setFocusable(true);
        addKeyListener(keyH);
        requestFocusInWindow();

        JOptionPane.showMessageDialog(this,
                "You’ve picked up food from Vista Café.\nDeliver it to Malek Hall avoiding guards!\nLet’s go!",
                "Mission 6", JOptionPane.INFORMATION_MESSAGE);

        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        gameThread = new Thread(this);
        gameThread.start();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        int FPS = 60;
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remaining = (nextDrawTime - System.nanoTime()) / 1000000;
                if (remaining > 0) Thread.sleep((long) remaining);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (missionEnded) return;

        missionPlayer.update();

        // Guard shooting
        for (Guard guard : guards) {
            guard.update();

            if (guard.readyToShoot()) {
                bullets.add(new Bullet(guard.x + tileSize / 2, guard.y + tileSize / 2, guard.direction));
                guard.resetShotTimer();
            }
        }


        // Bullet movement and collision
        for (Bullet bullet : bullets) {
            bullet.update();
            Rectangle bulletBox = bullet.getHitbox();
            Rectangle playerBox = new Rectangle(missionPlayer.worldX, missionPlayer.worldY, tileSize, tileSize);

            if (bullet.active && bulletBox.intersects(playerBox)) {
                missionFailed = true;
                endMission("You got hit!\nMission Failed.");
                return;
            }
        }

        bullets.removeIf(b -> !b.active);

        // Goal check
        if (Math.abs(missionPlayer.worldX - goalX) < tileSize &&
                Math.abs(missionPlayer.worldY - goalY) < tileSize) {
            endMission("You delivered the food!\nMission Complete!");
            return;
        }

        // Timer check
        long currentTime = System.currentTimeMillis();
        int elapsedTime = (int) ((currentTime - startTime) / 1000);

        if (elapsedTime >= timeLimitSeconds && !missionFailed) {
            missionFailed = true;
            endMission("The food spoiled!\nYou failed to deliver in time.");
        }

        System.out.println("Player: (" + missionPlayer.worldX / tileSize + ", " + missionPlayer.worldY / tileSize + ")");


    }

    public void endMission(String message) {
        if (missionEnded) return;

        missionEnded = true;

        // Stop game logic
        gp.player.isInMission = false;
        gp.missionActive = false;

        // Reset keys
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;

        // Show message, then dispose window safely
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, message);
            keyH.upPressed = false;
            keyH.downPressed = false;
            keyH.leftPressed = false;
            keyH.rightPressed = false;
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            gameThread = null;
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManager.drawFixed(g2);
        missionPlayer.draw(g2);

        for (Guard guard : guards) {
            guard.draw(g2);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }

        int timeLeft = Math.max(0, timeLimitSeconds - (int)((System.currentTimeMillis() - startTime) / 1000));
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.drawString("Time Left: " + timeLeft + "s", 20, 30);

        // Highlight goal
        g2.setColor(Color.YELLOW);
        g2.drawRect(goalX, goalY, tileSize, tileSize);
        g2.drawString("Malek Hall", goalX, goalY - 5);

        for (MapObject obj : missionObjects) {
            int worldX = obj.getX() * tileSize;
            int worldY = obj.getY() * tileSize;
            g2.drawImage(obj.getImage(), worldX, worldY, null);
        }

        g2.dispose();
    }
}