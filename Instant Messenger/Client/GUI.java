import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFrame.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
public class GUI{
    static String ip;
    static int port;
    JPanel topPanel = new JPanel();
    JPanel botPanel = new JPanel();
    JFrame frame = new JFrame("Client Box");
    JTextField tf;
    PrintWriter out;
    BufferedReader in;
    Client c;
    
    String userName = null;
    
    JFrame newFrame = new JFrame("Client Box");
    JTextArea text = new JTextArea(5, 30);
    
    public void onExit(){
        try{
            out = new PrintWriter(c.getSocket().getOutputStream(), true);
            out.println("\n\n");
            c.getSocket().close();
            System.exit(0);
        }
        catch(NullPointerException e){
            System.out.println("NPE");
            System.exit(1);
        }
        catch(IOException e){
            System.out.println("IOE");
            System.exit(1);
        }
    }
    
    
    public String time(){
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public GUI(){
        try{
            this.c = new Client();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        start();
    }
    
    public void start(){
        //Creating the Frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1300, 500);
        frame.setFocusableWindowState(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
            }
            public void windowDeactivated(WindowEvent evt) {
                frame.setState(Frame.ICONIFIED);
            }
        });
        
        //Creating the Login and adding components
        JLabel IPLabel = new JLabel("Enter IP (Format xxx.xxx.xxx.xxx): ");
        JTextField IPTf = new JTextField(15); // accepts upto 15 characters
        JLabel portLabel = new JLabel("Enter PORT (No larger than 65,535): ");
        JTextField portTf = new JTextField(5); // accepts upto 5 characters
        JButton connect = new JButton("Connect");
        frame.getRootPane().setDefaultButton(connect);
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String ip = IPTf.getText();
                int port = Integer.parseInt(portTf.getText());
                c.setSocket(ip, port);
                if(c.getSocket() != null){
                    connect();
                }
            }
        });
        topPanel.add(IPLabel); // Components Added using Flow Layout
        topPanel.add(IPTf);
        topPanel.add(portLabel);
        topPanel.add(portTf);
        topPanel.add(connect);

        topPanel.setLayout(new FlowLayout());
        frame.add(BorderLayout.SOUTH, topPanel);
        frame.setVisible(true);
    }
    
    public void connect(){
        topPanel.removeAll();
        JLabel userLabel = new JLabel("Enter a username (No longer than 16 characters): ");
        JTextField userTF = new JTextField(16); // accepts upto 5 characters
        JButton enter = new JButton("Enter");
        frame.getRootPane().setDefaultButton(enter);
        
        Username u = new Username();
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                u.setUsername(userTF.getText());
                if((u.getUsername()).equals("")){
                    return;
                }
                else{            
                    send(u.getUsername());
                    createBot();
                }
            }
        });
        
        topPanel.add(userLabel);
        topPanel.add(userTF);
        topPanel.add(enter);
        topPanel.updateUI();

    }
    
    public void createBot(){
        frame.setVisible(false);
        
        newFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newFrame.setSize(1300, 500);
        
        //Creating the panel at bottom and adding components
        JLabel label = new JLabel("Enter Text: ");
        tf = new JTextField(64); // accepts upto 64 characters
        JButton send = new JButton("Send");
        
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                send(tf.getText());
                tf.setText("");
            }
        });
        
        botPanel.add(label); // Components Added using Flow Layout
        botPanel.add(tf);
        botPanel.add(send);
        createTextArea();
        
        String[] colors = {"Default (White)", "Dark (Black)", "Blue", "Red"};
        MenuBar mb = new MenuBar();
        ActionListener al = new ThemeChanger(text); 
        mb.add(mb.createMenu("Theme", colors, al));
        
        ActionListener l = new ClearText(text);
        mb.add(mb.createMenu("Clear", l));

        newFrame.setJMenuBar(mb.getBar());
        
        newFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
            }
            public void windowDeactivated(WindowEvent evt) {
                newFrame.setState(Frame.ICONIFIED);
            }
        });
        
        newFrame.getRootPane().setDefaultButton(send);
        newFrame.add(botPanel, BorderLayout.SOUTH);
        newFrame.setVisible(true);
        
    }
    
    public void createTextArea(){
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(1300, 500);
        newFrame.setAlwaysOnTop(false);
        text = new JTextArea(5, 30);
        
        JScrollPane scroll = new JScrollPane(text);
        
        text.setEditable(false);
        
        scroll.setViewportBorder(new LineBorder(Color.BLUE));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        newFrame.add(scroll, BorderLayout.CENTER);
        newFrame.setVisible(true);
    }
    
    public void send(String text){
        try{
            if(text.equals("")){
                return;
            }
            else{
                out = new PrintWriter(c.getSocket().getOutputStream(), true);
                out.println(text);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void receive(){
       try{
            in = new BufferedReader(new InputStreamReader(c.getSocket().getInputStream()));
            String test = null;
            if((test = in.readLine()) != null) {
                text.append(test + " (sent at " + time() + ")" +  "\n");
                return;  
            }
            else{
                return;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }  
    }
    
    public Socket getClient(){
        return c.getSocket();
    }   
}