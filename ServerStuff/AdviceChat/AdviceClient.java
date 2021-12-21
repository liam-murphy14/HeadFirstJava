import java.io.*;
import java.net.*;

public class AdviceClient{

    public static void main(String[] args){
        AdviceClient client = new AdviceClient();
        client.go();
    }
    public void go(){
        try{
            Socket s = new Socket("127.0.0.1", 4242);

            InputStreamReader inStream = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(inStream);

            String advice = reader.readLine();
            System.out.println("Today you should: " + advice);

            reader.close();
            s.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}