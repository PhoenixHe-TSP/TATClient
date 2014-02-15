package fdu.kernelpanic.core.user;

/**
 * Created by Phoenix on 14-2-12.
 */
public class TATUser {
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

    public void setPassword(String password) {
        MD5password = MD5(password);
    }

    public String getMD5password() {
        return MD5password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMD5password(String password) {
        this.MD5password = password;
    }
}
