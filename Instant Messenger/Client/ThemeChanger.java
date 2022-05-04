import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class ThemeChanger implements ActionListener{
    
    JTextArea text;
    public ThemeChanger(JTextArea text){
        this.text = text;
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Default (White)")){
            text.setBackground(Color.WHITE);
            text.setForeground(Color.BLACK);
        }
        else if(e.getActionCommand().equals("Dark (Black)")){
            text.setBackground(Color.BLACK);
            text.setForeground(Color.WHITE);
        }
        else if(e.getActionCommand().equals("Red")){
            text.setBackground(Color.RED);
            text.setForeground(Color.BLACK);
        }
        else if(e.getActionCommand().equals("Blue")){
            text.setBackground(Color.BLUE);
            text.setForeground(Color.WHITE);
        }
    }
    
}