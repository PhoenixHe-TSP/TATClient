package fdu.kernelpanic.core.user;

import fdu.kernelpanic.core.event.TATEvent;

/**
 * Created by Phoenix on 14-2-14.
 */
public class LoginCompleteEvent implements TATEvent {
    @Override
    public String getMessage() {
        return "Successfully Logged in";
    }
}
