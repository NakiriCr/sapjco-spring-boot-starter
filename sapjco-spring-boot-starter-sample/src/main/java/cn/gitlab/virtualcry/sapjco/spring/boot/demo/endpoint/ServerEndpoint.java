package cn.gitlab.virtualcry.sapjco.spring.boot.demo.endpoint;

import cn.gitlab.virtualcry.sapjco.beans.factory.JCoConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Optional;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@RestController
@RequestMapping("server")
public class ServerEndpoint {

    @Resource
    private JCoConnectionFactory        connectionFactory;

    @GetMapping("names")
    public Collection<String> getAllServerNames() {
        return connectionFactory.getServers().keySet();
    }

    @GetMapping("{serverName}/settings")
    public JCoSettings getServerSettings(@PathVariable String serverName) {
        return Optional
                .ofNullable(this.connectionFactory.getServer(serverName))
                .orElseThrow(() -> new RuntimeException("Could not find server: [" + serverName + "]"))
                .getSettings();
    }

    @GetMapping("{serverName}/state")
    public String getState(@PathVariable String serverName) {
        return this.connectionFactory.getServer(serverName).getState().toString();
    }
}
