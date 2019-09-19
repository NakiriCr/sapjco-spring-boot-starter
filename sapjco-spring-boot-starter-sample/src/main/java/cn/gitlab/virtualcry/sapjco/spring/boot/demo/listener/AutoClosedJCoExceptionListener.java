package cn.gitlab.virtualcry.sapjco.spring.boot.demo.listener;

import cn.gitlab.virtualcry.sapjco.beans.factory.ConnectionFactory;
import cn.gitlab.virtualcry.sapjco.spring.annotation.JCoListener;
import cn.gitlab.virtualcry.sapjco.util.key.KeyGenerator;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerState;
import lombok.extern.slf4j.Slf4j;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@Slf4j
@JCoListener
public class AutoClosedJCoExceptionListener implements JCoServerExceptionListener {

    @Override
    public void serverExceptionOccurred(JCoServer server, String connectionId,
                                        JCoServerContextInfo serverCtx, Exception ex) {

        if (JCoServerState.DEAD.equals(server.getState())) {
            String uniqueKey = KeyGenerator.generateServerKey(server.getGatewayHost(), server.getGatewayService(), server.getProgramID());
            String serverName = ConnectionFactory.getServerName(uniqueKey);
            ConnectionFactory.releaseServer(serverName);
        }

    }

}
