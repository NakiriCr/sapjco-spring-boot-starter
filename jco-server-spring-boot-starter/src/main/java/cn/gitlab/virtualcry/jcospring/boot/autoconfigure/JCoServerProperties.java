package cn.gitlab.virtualcry.jcospring.boot.autoconfigure;

import cn.gitlab.virtualcry.jcospring.connect.config.JCoSettings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JCoProperties
 */
@Data
@ConfigurationProperties(prefix = JCoServerProperties.JCO_SERVER_PREFIX)
public class JCoServerProperties {

    public static final String JCO_SERVER_PREFIX = "jco";

    public Map<String, JCoSettings> servers = new LinkedHashMap<>();

}
