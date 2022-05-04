import java.net.*; 
import java.io.*; 
import java.util.*;
public class Broadcast extends Thread{
    protected ConnectionHandler client;
    protected List<ConnectionHandler> clientList = new ArrayList<>();
    
    public Broadcast(ConnectionHandler client, List<ConnectionHandler> clientList){
        this.client = client;
        this.clientList = clientList;
        this.start();
    }
    
    public void run() {
        try {
            String s;
            this.client.setClientName();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getClient().getInputStream()));
            while ((s = br.readLine()) != null){
                for(ConnectionHandler client: clientList){
                    client.writer(s, this.client.getClientName());
                }
            }
        } 
        catch(IOException e) {
            System.out.println("");
        }
    }
}