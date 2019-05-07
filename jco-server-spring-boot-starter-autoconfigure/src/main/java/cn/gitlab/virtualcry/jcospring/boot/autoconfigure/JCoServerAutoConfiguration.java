package cn.gitlab.virtualcry.jcospring.boot.autoconfigure;

import cn.gitlab.virtualcry.jcospring.connect.server.semaphore.JCoServerCreatedOnErrorSemaphore;
import cn.gitlab.virtualcry.jcospring.connect.wrapper.JCoServerWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Auto configuration
 *
 * @author VirtualCry
 */
@Configuration
@EnableConfigurationProperties(JCoServerProperties.class)
public class JCoServerAutoConfiguration implements InitializingBean {

    private final JCoServerProperties properties;

    public JCoServerAutoConfiguration(JCoServerProperties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        // duplicate check
         properties.getServers().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> "gatewayHost: " + entry.getValue().getGatewayHost()
                                + " | " + "gatewayService: " + entry.getValue().getGatewayService()
                                + " | " + "programId: " + entry.getValue().getProgramId(),
                        Map.Entry::getKey,
                        (oldValue, newValue) -> oldValue + "," + newValue
                ))
                 .forEach((uniqueKey, serverNames) -> {
                     if (serverNames.split(",").length > 1)
                         throw new JCoServerCreatedOnErrorSemaphore("Duplicate settings: [" +
                                 uniqueKey + "] with server: [" + serverNames + "]");
                 });

         // start server
        properties.getServers()
                .forEach((serverName, settings) -> new JCoServerWrapper(settings).start());
    }

}
