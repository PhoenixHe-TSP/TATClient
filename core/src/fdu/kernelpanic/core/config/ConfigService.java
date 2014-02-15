package fdu.kernelpanic.core.config;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fdu.kernelpanic.core.JsonModel;
import fdu.kernelpanic.core.event.EventBusService;
import fdu.kernelpanic.core.service.ServiceManager;
import fdu.kernelpanic.core.service.TATService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Phoenix on 14-2-12.
 */
public class ConfigService extends TATService {
    public final static String fileDir =
            Environment.getExternalStorageDirectory() + "/fdu.kernelpanic.TATClient";
    public final static String filePath = fileDir + "/config.json";
    private final static String assetsFilePath = "config.json";

    private Activity mainActivity = null;

    private static ObjectMapper om = new ObjectMapper();
    private JsonModel data;

    @Override
    protected void doInit() {
        Log.d("ConfigService", filePath);
        File fd = new File(filePath);
        try {
            if (!fd.exists()) {
                InputStream is = mainActivity.getAssets().open(assetsFilePath);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                new File(fileDir).mkdirs();
                OutputStream os = new FileOutputStream(fd);
                os.write(buffer);
                os.flush();
                os.close();
            }

            InputStream is = new FileInputStream(fd);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            data = new JsonModel(buffer.toString());
        } catch (Exception e) {
            Log.e("ConfigService", "Something wrong with ConfigService", e);
        }
    }

    @Override
    protected void doStart() {
    }

    @Override
    protected void doStop() {
    }

    public Activity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * @return The root node of the config file
     */
    public static ObjectNode getRootNode(InputStream input) throws IOException {
        return (ObjectNode) om.readTree(input);
    }

    /**
     * Get the node information by the path
     *
     *
     * @param t    The root node
     * @param path The path of the node you want, for example "/server/config"
     * @return The JsonNode under the path of the root node
     */
    public static JsonNode getNode(JsonNode t, String path) {
        String[] p = path.split("\\/");
        for (String node : p) {
            if (node.equals("")) continue;
            t = t.get(node);
        }
        return t;
    }

    /**
     * @return The JsonNode under the path of the config
     *         files' root node
     */
    public JsonNode getNode(String path) {
        return data.getNode(path);
    }

    public ObjectNode getObjectNode(String path) {
        return (ObjectNode) getNode(path);
    }

    public void remove(String path) {
        data.remove(path);
    }

    public void set(String path, String value) {
        data.set(path, value);
    }

    /**
     * Get the node information by the path
     *
     * @param t    The root node
     * @param path The path of the node you want, for example "/server/config"
     * @return The String value of the JsonNode under the path of the root node
     */
    public static String get(ObjectNode t, String path) {
        return getNode(t, path).asText();
    }

    /**
     * @return The string value of the node under the
     *         path of the config files' root node
     */
    public String get(String path) {
        return data.get(path);
    }

    public List<String> getStringArray(String path) {
        List<String> ret = new ArrayList<>();
        for (Iterator<JsonNode> it = getNode(path).elements(); it.hasNext(); )
            ret.add(it.next().asText());
        return ret;
    }

    public void save() {
        try {
            String val = data.toString();
            OutputStream os = new FileOutputStream(filePath);
            os.write(val.getBytes());
            os.flush();os.close();
            ServiceManager.get().service(EventBusService.class).post(new ConfigSaveEvent());
        } catch (IOException e) {
            Log.e("ConfigService", "Cannot save config.json!", e);
        }
    }
}
