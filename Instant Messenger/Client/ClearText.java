import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class ClearText implements ActionListener{
    
    JTextArea text;
    public ClearText(JTextArea text){
        this.text = text;
    }
    
    public void actionPerformed(ActionEvent e){
        text.setText("Cleared!\n");
    }
}