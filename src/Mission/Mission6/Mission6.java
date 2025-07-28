package Mission.Mission6;

import main.GamePanel;

import javax.swing.*;

public class Mission6 extends JFrame {

    public Mission6(GamePanel gp) {
        gp.missionActive = true;
        setTitle("Mission 6 - Food Delivery");
        Mission6Panel panel = new Mission6Panel(gp);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}
