package cn.gitlab.virtualcry.sapjco.spring.boot.demo.listener;

import cn.gitlab.virtualcry.sapjco.beans.factory.JCoConnectionFactory;
import cn.gitlab.virtualcry.sapjco.spring.annotation.JCoListener;
import cn.gitlab.virtualcry.sapjco.util.key.KeyGenerator;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerState;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@Slf4j
@JCoListener
public class AutoClosedJCoExceptionListener implements JCoServerExceptionListener {

    @Resource
    private JCoConnectionFactory        connectionFactory;


    @Override
    public void serverExceptionOccurred(JCoServer server, String connectionId,
                                        JCoServerContextInfo serverCtx, Exception ex) {

        if (JCoServerState.DEAD.equals(server.getState())) {
            String uniqueKey = KeyGenerator.generateServerKey(server.getGatewayHost(), server.getGatewayService(), server.getProgramID());
            String serverName = this.connectionFactory.getServerName(uniqueKey);
            this.connectionFactory.releaseServer(serverName);
        }

    }
}
