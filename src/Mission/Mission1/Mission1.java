package Mission.Mission1;
import main.GamePanel;
import main.KeyHandler;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Mission1 extends JFrame {
    public Mission1(GamePanel gp) {
        gp.missionActive = true;
        setTitle("Solve the Maze");
        Mission1Panel panel = new Mission1Panel(gp, gp.keyH, gp.player);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}
