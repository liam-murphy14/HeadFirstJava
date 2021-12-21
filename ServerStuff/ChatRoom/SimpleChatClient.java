import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient{
   
    JFrame frame;
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args){
        new SimpleChatClient().go();
    }

    public void go(){
        frame = new JFrame("Chat Room");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton send = new JButton("Send");
        send.addActionListener(new SendButtonListener());
        mainPanel.add(scroller);
        mainPanel.add(outgoing);
        mainPanel.add(send);
        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    private void setUpNetworking(){
        try{
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader inStream = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(inStream);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Network established");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            try{
                writer.println(outgoing.getText());
                writer.flush();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public class IncomingReader implements Runnable{
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}