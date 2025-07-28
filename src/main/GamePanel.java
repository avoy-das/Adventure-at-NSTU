package main;

import entity.Player;
import tile.TileManager;
import Mission.Mission3.Mission3;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 20;
    public int maxScreenRow = 16;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(tileM);
    public Player player = new Player(this, keyH);
    public MissionManager missionManager;
    public boolean missionActive = false;
    public Mission3 mission3;
    public Mission.Mission5.Mission5 mission5;


    Thread gameThread;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(keyH);

        player.cChecker = cChecker;
        missionManager = new MissionManager(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (long)1000000000 / FPS;
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
        player.update();
        if (mission3 != null && mission3.active) {
            mission3.update();
        }

        if (mission5 != null && mission5.active) {
            mission5.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        if (mission3 != null && mission3.active) {
            mission3.draw(g2);


            boolean prevState = player.isInMission;
            player.isInMission = false;
            player.draw(g2);
            player.isInMission = prevState;
        } else {
            player.draw(g2);
        }

        if (mission5 != null && mission5.active) {
            mission5.draw(g2);

            boolean prev = player.isInMission;
            player.isInMission = false;
            player.draw(g2);
            player.isInMission = prev;
        } else {
            player.draw(g2);
        }
    }
}
