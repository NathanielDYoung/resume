import java.net.*; 
import java.io.*; 
import java.util.*; 
public class ConnectionHandler{
    private final Socket clientSocket;
    protected PrintWriter out;
    private String clientName;
    
    public ConnectionHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        try{
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String getClientName(){
        return clientName;
    }
    
    public void setClientName(){
        while(true){
            try{
                this.out = new PrintWriter(clientSocket.getOutputStream(), true);
                String s;
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while ((s = br.readLine()) != null && s != "") {
                    this.clientName = s;
                    break;
                }
                break;
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public Socket getClient(){
        return this.clientSocket;
    }
    
    public void writer(String s, String user){
        try{
            if(s.equals("\n\n")){
                this.out.close();
            }
            else{
                this.out = new PrintWriter(clientSocket.getOutputStream(), true);
                this.out.println(user + ": " + s);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}