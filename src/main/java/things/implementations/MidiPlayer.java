package things.implementations;

import things.implementations.additions.PlayerMetaEventListener;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import java.io.InputStream;

public class MidiPlayer extends ExtendedPlayer {
    private Sequencer sequencer;

    public MidiPlayer(InputStream stream) {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(stream);
            setState(PREFETCHED);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void prefetch() {
        // TODO: write method logic
        setState(PREFETCHED);
    }

    @Override
    public void start() {
        if (getState() != STARTED) {
            if (getState() == UNREALIZED || getState() == REALIZED) {
                prefetch();
            }
            sequencer.setMicrosecondPosition(0);
            sequencer.start();
            sequencer.addMetaEventListener(new PlayerMetaEventListener(this));
            setState(STARTED);
        }
    }

    @Override
    public void stop() {
        sequencer.stop();
        setState(REALIZED);
    }

    @Override
    public void close() {
        // TODO: write method logic
    }
}