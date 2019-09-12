package cn.gitlab.virtualcry.sapjco.spring.boot.autoconfigure;

import cn.gitlab.virtualcry.sapjco.beans.factory.ConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.Connections;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import cn.gitlab.virtualcry.sapjco.server.semaphore.JCoServerCreatedOnErrorSemaphore;
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

    private final JCoConnectionProperties               properties;

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
       // duplicate check.
       connectionProperties.entrySet().stream()
               .collect(Collectors.toMap(
                       entry -> entry.getValue().getUniqueKey(Connections.SERVER),
                       Map.Entry::getKey,
                       (oldValue, newValue) -> oldValue + "," + newValue
               ))
               .forEach((uniqueKey, serverNames) -> {
                   if (serverNames.split(",").length > 1)
                       throw new JCoServerCreatedOnErrorSemaphore("Duplicate settings: [" +
                               uniqueKey + "] with server: [" + serverNames + "]");
               });

       // start server
       connectionProperties.forEach((serverName, settings) -> ConnectionFactory.getOrCreateServer(serverName, settings).start());
   };

    /**
     * Create connections for client.
     */
    private final Consumer<Map<String, JCoSettings>> createClientConnections = connectionProperties -> {
        // start client
        connectionProperties.forEach(ConnectionFactory::getOrCreateClient);
    };

}
