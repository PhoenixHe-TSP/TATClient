package fdu.kernelpanic.core.service;

/**
 * Created by Phoenix on 14-2-12.
 */
public abstract class TATService {
    private boolean inited = false;
    private boolean started = false;

    public final void init() {
        if (inited)
            return;
        doInit();
        inited = true;
    }

    public final void start() {
        init();
        if (started)
            return;
        started = true;
        doStart();
    }

    public final void stop() {
        if (!started)
            return;
        started = false;
        doStop();
    }

    public boolean getRunningState() {
        return started;
    }

    protected abstract void doInit();
    protected abstract void doStart();
    protected abstract void doStop();
}
