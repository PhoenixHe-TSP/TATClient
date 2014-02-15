package fdu.kernelpanic.core.user;

import fdu.kernelpanic.core.config.ConfigService;
import fdu.kernelpanic.core.event.EventBusService;
import fdu.kernelpanic.core.network.NetworkService;
import fdu.kernelpanic.core.service.ServiceManager;
import fdu.kernelpanic.core.service.TATService;

/**
 * Created by Phoenix on 14-2-12.
 */
public class UserService extends TATService {
    private ConfigService cs;
    private EventBusService ebs;
    private NetworkService ns;

    public enum Status {
        NullUser, NotLogged, Logged
    }

    private Status status;

    private String username, MD5password;

    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array)
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @Override
    protected void doInit() {
        cs = ServiceManager.get().service(ConfigService.class);
        ebs = ServiceManager.get().service(EventBusService.class);
        ns = ServiceManager.get().service(NetworkService.class);
        username = cs.get("user/username");
        MD5password = cs.get("user/password");
        status = username.equals("") ? Status.NullUser : Status.NotLogged;
    }

    @Override
    protected void doStart() {
    }

    @Override
    public void doStop() {
    }

    public Status getStatus() {
        return status;
    }

    public void setUsername(String username) throws CannotModifyUserException {
        if (status == Status.Logged)
            throw new CannotModifyUserException();
        this.username = username;
        cs.set("user/username", username);
        cs.save();
        status = username.equals("") ? Status.NullUser : Status.NotLogged;
    }

    public void setPassword(String password) throws CannotModifyUserException {
        if (status == Status.Logged)
            throw new CannotModifyUserException();
        this.MD5password = MD5(password);
        cs.set("user/password", password);
        cs.save();
    }

    public String getUsername() {
        return username;
    }

    public String getMD5password() {
        return MD5password;
    }

    public void login() {
        if (status == Status.Logged)
            ebs.post(new LoginFailedEvent("User have been Logged in"));
        else if (status == Status.NullUser)
            ebs.post(new LoginFailedEvent("Null User"));
        else {
            //Do Login

        }
    }
}
