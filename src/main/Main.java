package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window1 = new JFrame();
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window1.setResizable(false);
        window1.setTitle("Adventure at NSTU");
        GamePanel gamePanel = new GamePanel();
        window1.add(gamePanel);
        //window1.setPreferredSize(new Dimension(1200,600));ll
        window1.pack();
        window1.setLocationRelativeTo(null);
        window1.setVisible(true);
        gamePanel.startGameThread();
    }
}
