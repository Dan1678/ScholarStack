package MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Managers.BackupManager;
class TopMenuBar extends JMenuBar{
    private JMenuItem backupMenuItem;
    private BackupManager backupManager;

    //top menu bar

     // Menu items EXAMPLE
     private JMenu fileMenu;

     public TopMenuBar() {

         fileMenu = new JMenu("File");

         // create menuitems (may want to impliment as an array list as this grows)
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

         backupMenuItem = new JMenuItem("Back up");
         BackupManager backupManager = new BackupManager(
                "ec2-54-246-1-94.eu-west-1.compute.amazonaws.com",
                "d6rkhhv2aujh36",
                "mixbutdugvnycu",
                "03f7fa8bfe5bfc30d6776369a8163f90164d68fcebaeecc32f073d7c4a334b94");

        String backupFilePath = "C:\\SS_Backup\\heroku_backup2.backup";
         backupMenuItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 backupManager.createBackup("C:\\SS_Backup\\heroku_backup2.backup");
             }
         });
         add(backupMenuItem);

     }

}
