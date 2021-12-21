import java.io.*;
import java.net.*;

public class AdviceServer{

    String[] advice = {
        "Take smaller bites",
        "Go for the tight jeans. I promise they don't make you look fat",
        "One word: inappropriate", 
        "Just for today, be honest. Tell Steve what you really think",
        "You may want to reevaluate your haircut"
    };
    ServerSocket server;

    public static void main(String[] args){
        AdviceServer server = new AdviceServer();
        server.go();
    }
    
    public void go(){
        try{
            server = new ServerSocket(4242);

            while(true){

                Socket socket = server.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getAdvice(){
        int random = (int) (Math.random() * advice.length);
        return advice[random];
    }
}