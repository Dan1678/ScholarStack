package MainUI;

import Managers.BackupManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TopMenuBar extends JMenuBar{
    // class to include top bar at the top of the page with different functionalities
    private JMenu fileMenu;
    private JMenuItem backupMenuItem;
    private BackupManager backupManager;
    private JFrame parentFrame;


    //top menu bar

    public TopMenuBar(JFrame parentFrame) {

        // Menu items EXAMPLE
        JMenu fileMenu = new JMenu("File");

         // create menu items (may want to impliment as an array list as this grows)
         JMenuItem exit = new JMenuItem("Exit");

         // Add action listener to the exit item
         exit.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0); // Exits the application
             }
         });
         // add menu items to menu
         fileMenu.add(exit);

         add(fileMenu);

         //EXAMPLE
         JMenuItem a, b, c;
         a = new JMenuItem("a");
         b = new JMenuItem("b");
         c = new JMenuItem("c");

         JMenu exampleMenu = new JMenu("Example");
         exampleMenu.add(a);
         exampleMenu.add(b);
         exampleMenu.add(c);

         JMenu exampleSubMenu = new JMenu("submenu");

         JMenuItem d, e, f;
         a = new JMenuItem("d");
         b = new JMenuItem("e");
         c = new JMenuItem("f");

         exampleSubMenu.add(a);
         exampleSubMenu.add(b);
         exampleSubMenu.add(c);

         exampleMenu.add(exampleSubMenu);

         add(exampleMenu);
         //END EXAMPLE


//add back up option to top bar
         JMenuItem backupMenuItem = new JMenuItem("Back up");
         backupMenuItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 chooseBackupFilePath();
             }
         });
         add(backupMenuItem);
     }

     //create option to allow user to choose the file for the back-up and error handling
    private void chooseBackupFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Backup File Path");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Backup Files (*.backup)", "backup"));


        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (backupManager != null) {
                backupManager.createBackup(filePath);
            } else {
                System.err.println("BackupManager is not initialized!");
            }}
    }

//initialize backup manager
    public void setBackupManager(BackupManager backupManager) {
        this.backupManager = backupManager;
    }
}
