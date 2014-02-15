package fdu.kernelpanic.core.user;

import fdu.kernelpanic.core.event.TATEvent;

/**
 * Created by Phoenix on 14-2-14.
 */
public class LoginFailedEvent implements TATEvent {
    private String reason;

    public LoginFailedEvent(String reason) {
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "Failed to Login. " + reason;
    }
}
