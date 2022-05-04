import java.net.*;
import java.io.*;
public class Client {
    private Socket socket = null;
    public String ip;
    public int port;
    
    public Client() throws IOException{
    }
    
    public void setSocket(String ip, int port){
        try{
            this.socket = new Socket(ip, port);
        }
        catch(IOException e){
            System.out.println("Could not connect to server with IP " + ip + " and port " + port);
        }
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public void setIP(String ip){
        this.ip = ip;
    }
    
    public void setPort(int port){
        this.port = port;
    }
    
    public String getIP(){
        return ip;
    }
    
    public int getPort(){
        return port;
    }
}