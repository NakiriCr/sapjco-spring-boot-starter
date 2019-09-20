package cn.gitlab.virtualcry.sapjco.spring.boot.autoconfigure;

import cn.gitlab.virtualcry.sapjco.beans.factory.JCoConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import cn.gitlab.virtualcry.sapjco.server.JCoServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Auto configuration
 *
 * @author VirtualCry
 */
@Configuration
@EnableConfigurationProperties(JCoConnectionProperties.class)
public class JCoConnectionAutoConfiguration implements InitializingBean {

    private final JCoConnectionProperties   properties;
    private final JCoConnectionFactory      connectionFactory;

    public JCoConnectionAutoConfiguration(JCoConnectionProperties properties,
                                          JCoConnectionFactory connectionFactory) {
        this.properties = properties;
        this.connectionFactory = connectionFactory;
    }


    @Override
    public void afterPropertiesSet() {
        // create server connections
        this.createServerConnections.accept(this.connectionFactory, this.properties.getServers());
        // create client connections
        this.createClientConnections.accept(this.connectionFactory, this.properties.getClients());
    }


    /**
     * Create connections for server.
     */
    private final BiConsumer<JCoConnectionFactory,
            Map<String, JCoSettings>> createServerConnections = (connectionFactory, connectionProperties) -> {
       // create and start server.
       connectionProperties.entrySet().stream()
               .map(entry -> connectionFactory.createServer(entry.getKey(), entry.getValue()))
               .collect(Collectors.toList())
               .forEach(JCoServer::start);
   };

    /**
     * Create connections for client.
     */
    private final BiConsumer<JCoConnectionFactory,
            Map<String, JCoSettings>> createClientConnections = (connectionFactory, connectionProperties) -> {
        // create client.
        connectionProperties.forEach(connectionFactory::createClient);
    };
}
