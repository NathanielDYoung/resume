import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MenuBar{
    
    JMenuBar bar; 
    public MenuBar(){
        this.bar = new JMenuBar();
    }
    
    public JMenu createMenu(String menuName, ActionListener a){
        JMenu menu;
        menu = new JMenu(menuName);
        JMenuItem mi = new JMenuItem("Clear");
        mi.addActionListener(a);
        menu.add(mi);
        return menu;
    }
    
    public JMenu createMenu(String menuName, String[] menuItems, ActionListener a){
        
        JMenu menu;
        menu = new JMenu(menuName);
        for(int i = 0; i < menuItems.length; i++){
            JMenuItem mi = new JMenuItem(menuItems[i]);
            mi.addActionListener(a);
            menu.add(mi);
        }
        
        return menu;
    }
    
    public void add(JMenu menu){
        bar.add(menu);
    }
    
    public JMenuBar getBar(){
        return bar;
    }
}