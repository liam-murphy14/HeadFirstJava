package MusicPlayer.MusicPractice;

import javax.sound.midi.*;

public class MiniMusicCmdLine{

    public static void main(String[] args){
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        if (args.length < 2){
            System.out.println("Dont forget the note and instrument");
        }
        else{
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument, note);
        }
    }

    public void play(int instrument, int note){
        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInst = new MidiEvent(first, 1);
            track.add(changeInst);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}