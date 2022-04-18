package javax.microedition.media;

// TODO: write interface methods
public interface Player extends Controllable {
    public static final int UNREALIZED = 100;
    public static final int REALIZED = 200;
    public static final int PREFETCHED = 300;
    public static final int STARTED = 400;
    public static final int CLOSED = 0;
    public static final long TIME_UNKNOWN = -1;

    public int getState();

    public void start();

    public void stop();
}