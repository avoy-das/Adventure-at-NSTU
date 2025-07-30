package Mission.Mission2;

import main.GamePanel;
import javax.swing.*;

public class Mission2 extends JFrame {

    public Mission2(GamePanel gp) {
        setTitle("Mission 2 Puzzle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        Mission2Panel panel = new Mission2Panel(gp);
        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        gp.missionActive = false;
        gp.keyH.upPressed = false;
        gp.keyH.downPressed = false;
        gp.keyH.leftPressed = false;
        gp.keyH.rightPressed = false;

    }
}
