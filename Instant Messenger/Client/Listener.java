import java.io.*;
import java.net.*;
import java.util.*;
public class Listener extends Thread{    
    GUI g;
    public Listener(GUI g){
        this.g = g;
        this.start();
    }
    public void run(){
        g.receive();
    }
}