package cn.gitlab.virtualcry.jcospring.boot.autoconfigure;

import cn.gitlab.virtualcry.jcospring.cache.JCoServerCache;
import cn.gitlab.virtualcry.jcospring.connect.wrapper.JCoServerWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
        long distinctServerCount = properties.getServers().values().stream()
                .map(settings -> settings.getGatewayHost() + "|" + settings.getGatewayService() + "|" + settings.getProgramId())
                .distinct()
                .count();
        if (distinctServerCount != properties.getServers().values().size())
            throw new Exception("There're some servers with the same gatewayHost & gatewayService & programId. Please check configurations.");

        properties.getServers().forEach((serverName, settings) -> {
            JCoServerWrapper server = new JCoServerWrapper(settings);
            server.start();
            JCoServerCache.cache(serverName, server);
        });
    }

}
