package GUIPractice;

import java.awt.*;
import javax.swing.*;

public class MyDrawPanel extends JPanel{

        static final long serialVersionUID = 1232;
    public void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        int rstart = (int) (Math.random() * 256);
        int gstart = (int) (Math.random() * 256);
        int bstart = (int) (Math.random() * 256);

        Color start = new Color(rstart, gstart, bstart);

        int rend = (int) (Math.random() * 256);
        int gend = (int) (Math.random() * 256);
        int bend = (int) (Math.random() * 256);

        Color end = new Color(rend, gend, bend);

        GradientPaint gradient = new GradientPaint(70, 70, start, 170, 170, end);

        g2.setPaint(gradient);

        g2.fillOval(70, 70, 100, 100);
    }
}