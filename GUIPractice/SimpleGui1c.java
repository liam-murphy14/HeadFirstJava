package GUIPractice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGui1c{

    JFrame frame;
    JLabel label;
    public static void main(String[] args){
        SimpleGui1c gui = new SimpleGui1c();
        gui.makePanel();
    }

    public void makePanel(){
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton colorButton = new JButton("Click to change colors");
        colorButton.addActionListener(new ColorListener());
        
        JButton labelButton = new JButton("Click to change labels");
        labelButton.addActionListener(new LabelListener());

        label = new JLabel("Im a label");
        
        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.EAST, label);
        frame.getContentPane().add(BorderLayout.WEST, labelButton);

        frame.setSize(500, 500);
        frame.setVisible((true));
    }

    private class LabelListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            label.setText("hello");
        }
    }
    private class ColorListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            frame.repaint();
        }
    }
}