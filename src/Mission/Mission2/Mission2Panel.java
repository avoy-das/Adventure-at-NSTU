package Mission.Mission2;
import main.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Mission2Panel extends JPanel implements ActionListener {
    GamePanel gp;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<ImageIcon> images = new ArrayList<>();
    JButton firstbutton = null;
    JButton secondbutton = null;
    boolean firstClick = false;

    public Mission2Panel(GamePanel gp) {
        this.gp = gp;
        setPreferredSize(new Dimension(450, 450));
        setLayout(new GridLayout(3, 3));

        JOptionPane.showMessageDialog(this,
                "The first thing you see when you enter the campus is our logo.\nDo you remember what the logo looks likes?\nLets assemble it.",
                "Mission 2",
                JOptionPane.INFORMATION_MESSAGE);
        
        loadImages();
        createButtons();
    }

    public void loadImages() {
        for (int i = 1; i <= 9; i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/mission2/" + i + ".png"));
            icon.setDescription(String.valueOf(i - 1));
            images.add(icon);
        }
    }

    public void createButtons() {
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton(images.get(i));
            button.setPreferredSize(new Dimension(150, 150));
            button.addActionListener(this);
            buttons.add(button);
        }

        Collections.shuffle(buttons);
        for (int i = 0; i < buttons.size(); i++) {
            add(buttons.get(i));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!firstClick) {
            firstClick = true;
            firstbutton = clicked;
        } else {
            secondbutton = clicked;
            swapImages();
            firstClick = false;

            if (checkWin()) {
                JOptionPane.showMessageDialog(this, "You remember the logo , congratulations!");
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        }
    }

    public void swapImages() {
        if (firstbutton != secondbutton) {
            Icon icon1 = firstbutton.getIcon();
            Icon icon2 = secondbutton.getIcon();
            firstbutton.setIcon(icon2);
            secondbutton.setIcon(icon1);
        }
    }

    public boolean checkWin() {
        for (int i = 0; i < buttons.size(); i++) {
            ImageIcon icon = (ImageIcon) buttons.get(i).getIcon();
            String desc = icon.getDescription();
            if (!desc.equals(String.valueOf(i))) {
                return false;
            }
        }
        return true;
    }

}
