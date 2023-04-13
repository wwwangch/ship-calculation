package com.iscas.datasong.connector.conf;

import com.iscas.datasong.connector.Constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**

 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/29 18:36
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class HostInfo {
    private static final String HOST_PORT_SEPARATOR = ":";

    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final Map<String, String> hostProperties = new HashMap<>();

    public HostInfo(String host, int port, String user, String password, Map<String, String> properties) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        if (properties != null) {
            this.hostProperties.putAll(properties);
        }
    }


    public static HostInfo getInstance(String url, Properties info) {
        String user = (String) info.get("user");
        String password = (String) info.get("password");
        String[] strs = url.split(":");
        String host = strs[2].substring(strs[2].indexOf("//") + 2);
        int port = Integer.parseInt(strs[3].split("/")[0]);
        String dbName = strs[3].split("/")[1];
        Map<String, String> hostProperties = new HashMap<>(2) {{
            put(Constants.DBNAME_KEY, dbName);
        }};
        return new HostInfo(host, port, user, password, hostProperties);
    }

    public String getHost() {
        return this.host;
    }


    public int getPort() {
        return this.port;
    }


    public String getHostPortPair() {
        return this.host + HOST_PORT_SEPARATOR + this.port;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public Map<String, String> getHostProperties() {
        return Collections.unmodifiableMap(this.hostProperties);
    }

    public String getProperty(String key) {
        return this.hostProperties.get(key);
    }

    public String getDatabase() {
        String database = this.hostProperties.get(Constants.DBNAME_KEY);
        return database == null ? "" : database;
    }

}
