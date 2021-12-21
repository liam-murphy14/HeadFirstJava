package MusicPlayer.MusicAnimation;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MusicAnim{

    static JFrame frame = new JFrame("Music Video");
    static DrawingPanel dp;

    public static void main(String[] args){
        MusicAnim anim = new MusicAnim();
        anim.go();
    }

    public void setUpGui(){
        dp = new DrawingPanel();
        frame.setContentPane(dp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void go(){
        setUpGui();
        try{
            Sequencer seq = MidiSystem.getSequencer();
            seq.open();
            seq.addControllerEventListener(dp, new int[] {127});
            Sequence s = new Sequence(Sequence.PPQ, 4);
            Track track = s.createTrack();

            int r = 0;
            for (int i = 0; i < 200; i += 4){
                r = (int) ((Math.random() * 120) + 1);
                track.add(Utility.noteOn(r, i));
                track.add(Utility.makeEvent(176, 1, 127, 0, i));
                track.add(Utility.noteOff(r, i + 2));
            }

            seq.setSequence(s);
            seq.start();
            seq.setTempoInBPM(120);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private class DrawingPanel extends JPanel implements ControllerEventListener{
       
        /**
         *
         */
        private static final long serialVersionUID = -3556425654105592663L;
        boolean msg = false;

        public void controlChange(ShortMessage message){
            msg = true;
            repaint();
        }
        
        public void paintComponent(Graphics g){
            Graphics2D gs = (Graphics2D) g;
            if (msg){
                
                int rstart = (int) (Math.random() * 256);
                int gstart = (int) (Math.random() * 256);
                int bstart = (int) (Math.random() * 256);
                Color start = new Color(rstart, gstart, bstart);
                
                int rend = (int) (Math.random() * 256);
                int gend = (int) (Math.random() * 256);
                int bend = (int) (Math.random() * 256);
                Color end = new Color(rend, gend, bend);

                int startx = (int) (Math.random() * 261);
                int starty = (int) (Math.random() * 261);

                int width = (int) (Math.random() * 41);
                int height = (int) (Math.random() * 41);

                int endx = startx + width;
                int endy = starty + height;

                GradientPaint paint = new GradientPaint(startx, starty, start, endx, endy, end);
                gs.setPaint(paint);

                gs.fillRect(startx, starty, width, height);

                msg = false;
            }
        }
    }
}