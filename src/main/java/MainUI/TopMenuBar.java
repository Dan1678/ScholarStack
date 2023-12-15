package MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TopMenuBar extends JMenuBar{

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

     }

}
