package cn.gitlab.virtualcry.jcospring.cache;

import cn.gitlab.virtualcry.jcospring.connect.wrapper.JCoServerWrapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Server cache
 *
 * @author VirtualCry
 */
public class JCoServerCache {

    private static final Map<String, JCoServerWrapper> cacheHolder = new ConcurrentHashMap<>();

    public static JCoServerWrapper getServer(String serverName) {
        return cacheHolder.get(serverName);
    }

    public static void cache(String serverName, JCoServerWrapper server) {
        cacheHolder.put(serverName, server);
    }
}
