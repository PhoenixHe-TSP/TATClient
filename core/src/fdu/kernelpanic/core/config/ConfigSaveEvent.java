package fdu.kernelpanic.core.config;

import fdu.kernelpanic.core.event.TATEvent;

/**
 * Created by Phoenix on 14-2-14.
 */
public class ConfigSaveEvent implements TATEvent {
    @Override
    public String getMessage() {
        return "Saved config.json";
    }
}
