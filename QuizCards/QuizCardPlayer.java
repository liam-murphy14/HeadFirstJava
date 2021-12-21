package QuizCards;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class QuizCardPlayer{
    
    private JTextArea display;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private int index;
    private JFrame frame;
    private JButton nextButton;
    private boolean showAnswer;

    public static void main(String[] args){
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }
    
    public void go(){
        
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane qscroll = new JScrollPane(display);
        qscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        nextButton = new JButton("Show Question");
        mainPanel.add(qscroll);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);
    }

    private class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(showAnswer){
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                showAnswer = false;
            }
            else{
                display.setText("That was the last card");
                nextButton.setEnabled(false);
            }
        }
    }
    private class OpenMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }
    private void loadFile(File file){
        cardList = new ArrayList<QuizCard>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null){
                makeCard(line);
            }
            reader.close();
        }
        catch(Exception e){
            System.out.println("Couldnt load file");
            e.printStackTrace();
        }
        showNextCard();
    }
    
    private void makeCard(String line){
        String[] result = line.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("Made a card");
    }

    private void showNextCard(){
        currentCard = cardList.get(index);
        index++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        showAnswer = true;
    }
}