package cn.gitlab.virtualcry.sapjco.spring.boot.demo.endpoint;

import cn.gitlab.virtualcry.sapjco.beans.factory.ConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static cn.gitlab.virtualcry.sapjco.config.Connections.SERVER;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@RestController
public class ServerEndpoint {

    @PostMapping("createServer")
    public String createServer(@RequestBody JCoSettings settings) {
        String serverName = settings.getUniqueKey(SERVER);
        ConnectionFactory.createServer(serverName, settings).start();
        return serverName;
    }

    @GetMapping("releaseServer")
    public void releaseServer(String serverName) {
        ConnectionFactory.releaseServer(serverName);
    }

    @GetMapping("getState")
    public String getState(String serverName) {
        return ConnectionFactory.getServer(serverName).getState().toString();
    }

}
