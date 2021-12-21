package com.rmiservice;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniMusicService implements Service{

    private static final long serialVersionUID = 7952979244243158707L;
    private MyDrawPanel myPanel;

    public JPanel getGuiPanel(){
        JPanel mainPanel = new JPanel();
        myPanel = new MyDrawPanel();
        JButton playButton = new JButton("Play it!");
        playButton.addActionListener(new PlayItListener());
        mainPanel.add(myPanel);
        mainPanel.add(playButton);
        return mainPanel;
    }

    public class PlayItListener implements ActionListener{
        public void actionPerformed(ActionEvent av){
            try{
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();

                sequencer.addControllerEventListener(myPanel, new int[] {127});
                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();

                for(int i = 0; i < 100; i++){
                    int rNum = (int) ((Math.random() * 50) + 1);
                    if(rNum < 38){
                        track.add(makeEvent(144, 1, rNum, 100, i));
                        track.add(makeEvent(176, 1, 127, 0, i));
                        track.add(makeEvent(128, 1, rNum, 100, i + 2));
                    }
                }
                    sequencer.setSequence(seq);
                    sequencer.start();
                    sequencer.setTempoInBPM(220);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return event;
    }

    private class MyDrawPanel extends JPanel implements ControllerEventListener{

        private static final long serialVersionUID = -2622068720858092485L;
        boolean msg = false;

        public void controlChange(ShortMessage event){
            msg = true;
            repaint();
        }

        public Dimension getPreferredSize(){
            return new Dimension(300, 300);
        }

        public void paintComponent(Graphics graph){
            if(msg){
                Graphics2D g = (Graphics2D) graph;

                int r = (int) (Math.random() * 250);
                int gr = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);

                g.setColor(new Color(r, gr, b));

                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);

                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);

                g.fillRect(x, y, ht, width);
                msg = false;
            }
        }
    }
}