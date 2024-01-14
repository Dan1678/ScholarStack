package MainUI;

import javax.swing.*;
import java.awt.*;

public class WelcomeUser {
    public JPanel createWelcomePanel(String UserName) {
        JPanel newPanel = new JPanel();

        JLabel welcomeMessage = new JLabel("Welcome, " +UserName + "!");

        // Create a Font object specifying font name, style, and size
        Font customFont = new Font("CopperPlate", Font.BOLD, 16); // Change the font details as needed

        // Set the custom font to the JLabel
        welcomeMessage.setFont(customFont);
        welcomeMessage.setForeground(Color.DARK_GRAY);

        newPanel.add(welcomeMessage);
        return newPanel;
    }
}
