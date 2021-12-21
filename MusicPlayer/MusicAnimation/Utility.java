package MusicPlayer.MusicAnimation;

import javax.sound.midi.*;

public class Utility{
    
    public static MidiEvent makeEvent(
        int comd, int chan, int one, int two, int tick){

            MidiEvent event = null;
            try{
                ShortMessage a = new ShortMessage();
                a.setMessage(comd, chan, one, two);
                event = new MidiEvent(a, tick);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            return event;
    }

    public static MidiEvent noteOn(int one, int tick){
        MidiEvent event = makeEvent(144, 1, one, 100, tick);
        return event;
    }

    public static MidiEvent noteOff(int one, int tick){
        MidiEvent event = makeEvent (128, 1, one, 100, tick);
        return event;
    }
}