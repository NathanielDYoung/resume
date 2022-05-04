import java.net.*; 
import java.io.*; 
import java.util.*; 
public class Server{
    public static void main(String[] args) throws Exception {
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);
        List<ConnectionHandler> clientList = new ArrayList<>();
        while(true){
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();
            System.out.println("Connection Accepted");
            ConnectionHandler newClient = new ConnectionHandler(clientSocket);
            clientList.add(newClient);
            new Thread(new Broadcast(newClient, clientList));
        }
    }
}