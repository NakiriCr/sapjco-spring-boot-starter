package cn.gitlab.virtualcry.sapjco.spring.boot.demo.endpoint;

import cn.gitlab.virtualcry.sapjco.beans.factory.JCoConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @PostMapping("{serverName}/create")
    public void createServer(@PathVariable String serverName, @RequestBody JCoSettings settings) {
        this.connectionFactory.createServer(serverName, settings).start();
    }

    @GetMapping("{serverName}/settings")
    public JCoSettings getServerSettings(@PathVariable String serverName) {
        return Optional
                .ofNullable(this.connectionFactory.getServer(serverName))
                .orElseThrow(() -> new RuntimeException("Could not find server: [" + serverName + "]"))
                .getSettings();
    }

    @DeleteMapping("{serverName}/release")
    public void releaseServer(@PathVariable String serverName) {
        this.connectionFactory.releaseServer(serverName);
    }

    @GetMapping("{serverName}/state")
    public String getState(@PathVariable String serverName) {
        return this.connectionFactory.getServer(serverName).getState().toString();
    }
}