package cn.gitlab.virtualcry.sapjco.spring.boot.autoconfigure;

import cn.gitlab.virtualcry.sapjco.beans.factory.ConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import cn.gitlab.virtualcry.sapjco.server.JCoServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;
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

    public JCoConnectionAutoConfiguration(JCoConnectionProperties properties) {
        this.properties = properties;
    }


    @Override
    public void afterPropertiesSet() {
        // create server connections
        this.createServerConnections.accept(this.properties.getServers());
        // create client connections
        this.createClientConnections.accept(this.properties.getClients());
    }


    /**
     * Create connections for server.
     */
    private final Consumer<Map<String, JCoSettings>> createServerConnections = connectionProperties -> {
       // create and start server.
       connectionProperties.entrySet().stream()
               .map(entry -> ConnectionFactory.createServer(entry.getKey(), entry.getValue()))
               .collect(Collectors.toList())
               .forEach(JCoServer::start);
   };

    /**
     * Create connections for client.
     */
    private final Consumer<Map<String, JCoSettings>> createClientConnections = connectionProperties -> {
        // create client.
        connectionProperties.forEach(ConnectionFactory::createClient);
    };
}
