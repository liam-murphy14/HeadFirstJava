package GUIPractice;

import javax.swing.*;
import java.awt.*;

public class SimpleAnimation{
    int x = 70;
    int y = 70;

    public static void main (String[] args){
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Pan panel = new Pan();

        frame.getContentPane().add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for(int i = 0; i < 250; i ++){
            x++;
            y++;
            panel.repaint();
            try{
                Thread.sleep(50);
            }
            catch(Exception e){
                
            }
        }
    }

    private class Pan extends JPanel{
        /**
         *
         */
        private static final long serialVersionUID = 1358863037297025878L;

        public void paintComponent(Graphics g) {
            g.setColor(Color.red);
            g.fillOval(x, y, 40, 40);
        }
    }
}