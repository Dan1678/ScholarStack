package MainUI;

import javax.swing.*;

class TopMenuBar extends JMenuBar{

    //top menu bar

     // Menu items EXAMPLE
     private JMenu menu;

     public TopMenuBar() {
         //EXAMPLE
         // create menuitems
         JMenuItem a, b, c;
         a = new JMenuItem("MenuItem1");
         b = new JMenuItem("MenuItem2");
         c = new JMenuItem("MenuItem3");

         // add menu items to menu
         menu.add(a);
         menu.add(b);
         menu.add(c);

         add(menu);
     }

}
