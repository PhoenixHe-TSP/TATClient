package fdu.kernelpanic.core.service;

/**
 * Created by Phoenix on 14-2-12.
 */
public abstract class TATService {
    private boolean inited = false;
    private boolean started = false;

    public final void start() {
        if (!inited) {
            init();
            inited = true;
        }
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

    protected abstract void init();
    protected abstract void doStart();
    public abstract void doStop();
}
