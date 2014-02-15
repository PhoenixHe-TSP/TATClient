package fdu.kernelpanic.core;

import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Phoenix on 14-2-14.
 */
public class JsonModel {
    private static ObjectMapper om = new ObjectMapper();
    private ObjectNode rootNode;

    public JsonModel() {
        rootNode = om.createObjectNode();
    }

    public JsonModel(String s) throws IOException {
        rootNode = (ObjectNode) om.readTree(s);
    }

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
        return getNode(rootNode, path);
    }

    public ObjectNode getObjectNode(String path) {
        return (ObjectNode) getNode(path);
    }

    public static void set(ObjectNode t, String path, String value) {
        String[] p = path.split("\\/");
        for (int i = 0; i < p.length - 1; ++i) {
            if (!t.has(p[i]))
                t.put(p[i], om.getNodeFactory().objectNode());
            t = (ObjectNode) t.get(p[i]);
        }
        t.put(p[p.length-1], value);
    }

    public static void remove(ObjectNode t, String path) {
        try {
            String[] p = path.split("\\/");
            for (int i = 0; i < p.length - 1; ++i)
                t = (ObjectNode) t.get(p[i]);
            t.remove(p[p.length-1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String path) {
        remove(rootNode, path);
    }

    public void set(String path, String value) {
        set(rootNode, path, value);
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
        return get(rootNode, path);
    }

    public List<String> getStringArray(String path) {
        List<String> ret = new ArrayList<>();
        for (Iterator<JsonNode> it = getNode(path).elements(); it.hasNext(); )
            ret.add(it.next().asText());
        return ret;
    }

    @Override
    public String toString() {
        try {
            return om.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (JsonProcessingException e) {
            Log.e("JsonModel", "Json ERROR!", e);
        }
        return "{}";
    }
}
