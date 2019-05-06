package org.yanzx.template.sap.callback;

import cn.gitlab.virtualcry.jcospring.connect.server.handler.FunctionInvokeHandler;
import cn.gitlab.virtualcry.jcospring.extension.annotation.JCoFunctionHandler;
import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.server.JCoServerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Description:
 *
 * @author VirtualCry
 */
@JCoFunctionHandler("ZMM_TEST_CONN")
public class ZF_TEST2_CallbackHandler implements FunctionInvokeHandler {

    private static final Log _logger = LogFactory.getLog(ZF_TEST2_CallbackHandler.class);

    @Override
    public void handleRequest(JCoServerContext _serverCtx, JCoFunction _function) throws AbapException, AbapClassException {

        _logger.info("----------------------------- 开始 接收SAP主动推送数据 -------------------------------");

        /* SAP对象 -  */
        JCoTable _test = _function.getTableParameterList().getTable("IT_EKPO");
        /* Transfer */
//        List<Map<String, Object>> _test2 = JCoDataUtils.readJCoTable(_test);

        _logger.info("----------------------------- 结束 接收SAP主动推送数据 -------------------------------");
    }
}
