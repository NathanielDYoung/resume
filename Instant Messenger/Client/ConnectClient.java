import java.net.*;
import java.io.*;
import java.util.*;
public class ConnectClient{ 
    public static void main(String[] args) throws InterruptedException{
        GUI g = new GUI();
        Thread.sleep(10000);
        while(g.getClient() == null){
            Thread.sleep(10000);
        }
        
        boolean exit = false;
        while(true){
            Thread t = new Thread(new Listener(g));
            Thread.sleep(1000);
            t.stop();
        }
    }
}