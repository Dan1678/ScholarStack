package MainUI;

import javax.swing.*;
import java.awt.*;

public class WelcomeUser {
    //class to change the way the welcome user message is shown
    public JPanel createWelcomePanel(String UserName) {
        JPanel newPanel = new JPanel();

        JLabel welcomeMessage = new JLabel("Welcome, " +UserName + "!");
        Font customFont = new Font("CopperPlate", Font.BOLD, 16);

        // Set the custom font to the JLabel
        welcomeMessage.setFont(customFont);
        welcomeMessage.setForeground(Color.DARK_GRAY);

        newPanel.add(welcomeMessage);
        return newPanel;
    }
}
