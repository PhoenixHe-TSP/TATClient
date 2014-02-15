package fdu.kernelpanic.core.event;

import android.util.Log;
import com.google.common.eventbus.EventBus;
import fdu.kernelpanic.core.service.TATService;

import java.util.EventListener;

/**
 * Created by Phoenix on 14-2-14.
 */
public class EventBusService extends TATService {
    private EventBus eventBus;

    @Override
    protected void doInit() {
        eventBus = new EventBus();
    }

    @Override
    protected void doStart() {
    }

    @Override
    protected void doStop() {
    }

    public void register(EventListener el) {
        eventBus.register(el);
        Log.d("EventBus register", el.getClass().getName());
    }

    public void unregister(EventListener el) {
        eventBus.unregister(el);
        Log.d("EventBus unregister", el.getClass().getName());
    }

    public void post(TATEvent TATEvent) {
        eventBus.post(TATEvent);
        Log.d("EventBus post", TATEvent.getMessage());
    }
}
