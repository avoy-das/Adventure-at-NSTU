package Mission.Mission1;
import main.KeyHandler;
import entity.Player;
import tile.TileManager;
import main.GamePanel;
import javax.swing.*;
import java.awt.*;
import main.*;

public class Mission1Panel extends JPanel implements Runnable {
    int tileSize ;
    int maxWorldCol ;
    int maxWorldRow ;
    Player missionPlayer;
    GamePanel gp;
    KeyHandler keyH;
    TileManager missionTileManager;
    public int FPS = 60;
    Thread gameThread;
    int endX ,endY;
    int timeLimitSeconds = 200;
    long startTime;
    boolean missionComplete = false;
    boolean missionFailed = false;
    int returnX , returnY;
    public Mission1Panel(GamePanel gp, KeyHandler keyH, Player sourcePlayer) {
        this.gp = gp;
        this.keyH = keyH;
        this.tileSize = gp.tileSize;
        this.maxWorldCol = 31 ;
        this.maxWorldRow = 21 ;

        returnX = 33 * tileSize;
        returnY = 53 * tileSize;

        endX = 29 * tileSize;
        endY = 9 * tileSize;

        setPreferredSize(new Dimension(tileSize * maxWorldCol, tileSize * maxWorldRow));
        setBackground(Color.green);
        setDoubleBuffered(true);

        missionPlayer = new Player(gp, keyH);
        missionPlayer.setPosition(tileSize, tileSize);

        missionPlayer.isInMission = true;

        missionTileManager = new TileManager(gp, tileSize, maxWorldCol, maxWorldRow);
        missionTileManager.loadMap("/resources/maps/mazemap.txt");

        CollisionChecker missionCollisionChecker ;
        missionCollisionChecker = new CollisionChecker(missionTileManager);
        missionPlayer.cChecker = missionCollisionChecker;

        missionCollisionChecker.checkTiles(missionPlayer);

        gameThread = new Thread(this);
        gameThread.start();
        startTime = System.currentTimeMillis();

        setFocusable(true);
        addKeyListener(keyH);
        requestFocusInWindow();

        JOptionPane.showMessageDialog(this,
                "It's annual sports day ," +
                        "\nyou have to reach from Auditorium to Library building in 60 seconds." +
                        "\nBut the road is a maze.\nCan you reach within time?",
                "Mission 1",
                JOptionPane.INFORMATION_MESSAGE);
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
    }

    @Override
    public void run() {
        double drawInterval = (long)1000000000 / FPS ;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            System.nanoTime();
            update();
            repaint();
            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
                if (remainingTime > 0) {
                    Thread.sleep((long) remainingTime);
                }
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {

            }
        }
    }
    public void update() {
        if (missionComplete || missionFailed) return;

        missionPlayer.update();

        if (Math.abs(missionPlayer.worldX - endX) < tileSize &&
                Math.abs(missionPlayer.worldY - endY) < tileSize) {

            missionComplete = true;
            JOptionPane.showMessageDialog(this, "Congratulations!You have completed the mission!");
            gp.player.setPosition(returnX, returnY);

            gp.player.isInMission = false;
            gp.missionActive = false;
            keyH.upPressed = false;
            Window window = SwingUtilities.getWindowAncestor(this);
            window.dispose();
            return;
        }

        long currentTime = System.currentTimeMillis();
        int elapsedTime = (int)((currentTime - startTime) / 1000);
        if (elapsedTime >= timeLimitSeconds) {
            missionFailed = true;
            JOptionPane.showMessageDialog(this, "Time up\nBetter luck next time");
            gp.player.isInMission = false;
            gp.missionActive = false;

            keyH.upPressed = false;
            keyH.downPressed = false;
            keyH.leftPressed = false;
            keyH.rightPressed = false;

            Window window = SwingUtilities.getWindowAncestor(this);
            window.dispose();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        missionTileManager.drawFixed(g2);
        missionPlayer.draw(g2);

        g2.setColor(Color.yellow);
        int timeLeft = Math.max(0, timeLimitSeconds - (int)((System.currentTimeMillis() - startTime) / 1000));
        g2.drawString("Time Left  " + timeLeft + "s", 20, 30);
        g2.dispose();
    }

}


