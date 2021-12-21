import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChatServer{

    ArrayList<PrintWriter> clientOutStreams;

    public static void main(String[] args){
        new SimpleChatServer().go();
    }

    public class ClientHandler implements Runnable{
        
        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket client){
            try{
                sock = client;
                InputStreamReader inStream = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(inStream);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        public void run(){
            String message;
            try{
                while ((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void go(){
        clientOutStreams = new ArrayList<PrintWriter>();
        try{
            ServerSocket server = new ServerSocket(5000);

             while(true){
                Socket client = server.accept();
                PrintWriter writer = new PrintWriter(client.getOutputStream());
                clientOutStreams.add(writer);

                Thread t = new Thread(new ClientHandler(client));
                t.start();
                System.out.println("Connected.");
                server.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void tellEveryone(String s){
        Iterator<PrintWriter> iter = clientOutStreams.iterator();
        while(iter.hasNext()){
            try{
                PrintWriter writer = iter.next();
                writer.println(s);
                writer.flush();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}