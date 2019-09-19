package cn.gitlab.virtualcry.sapjco.spring.boot.autoconfigure;

import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JCoProperties
 */
@Data
@ConfigurationProperties(prefix = "jco")
public class JCoConnectionProperties {

    private final Map<String, JCoSettings>      servers;
    private final Map<String, JCoSettings>      clients;

    public JCoConnectionProperties() {
        this.servers = new LinkedHashMap<>();
        this.clients = new LinkedHashMap<>();
    }
}
