package Mission.Mission4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.GamePanel;

public class Mission4 extends JFrame {

    public Mission4(GamePanel gp) {
        setTitle("Mission 4 - Hello World in C");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JTextArea message = new JTextArea(
                "Write a complete Hello World program in C.\n\n" +
                        "With full syntax\n"

        );
        message.setEditable(false);
        message.setBackground(null);
        message.setBorder(null);
        message.setFont(new Font("SansSerif", Font.PLAIN, 14));
        message.setLineWrap(true);
        message.setWrapStyleWord(true);

        JTextArea codeInput = new JTextArea(10, 50);
        codeInput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(codeInput);

        JButton submitButton = new JButton("Submit");
        JLabel feedback = new JLabel(" ");

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(message, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(feedback, BorderLayout.WEST);
        bottom.add(submitButton, BorderLayout.EAST);
        panel.add(bottom, BorderLayout.SOUTH);
        gp.keyH.upPressed = false;
        gp.keyH.downPressed = false;
        gp.keyH.leftPressed = false;
        gp.keyH.rightPressed = false;
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        submitButton.addActionListener(e -> {
            String userCode = codeInput.getText();
            if (isValidFullCProgram(userCode)) {
                JOptionPane.showMessageDialog(this, "Mission Complete!");
                gp.missionActive = false;
                gp.keyH.upPressed = false;
                gp.keyH.downPressed = false;
                gp.keyH.leftPressed = false;
                gp.keyH.rightPressed = false;
                dispose();
            } else {
                feedback.setText("Incorrect or incomplete C code.\nTry again.");
            }
        });
    }

    public boolean isValidFullCProgram(String code) {
        String normalized = code.replaceAll("\\s+", "").toLowerCase();

        return normalized.contains("#include<stdio.h>") &&
                normalized.contains("intmain()") &&
                normalized.contains("printf(\"helloworld\");") &&
                normalized.contains("return0;");
    }
}
