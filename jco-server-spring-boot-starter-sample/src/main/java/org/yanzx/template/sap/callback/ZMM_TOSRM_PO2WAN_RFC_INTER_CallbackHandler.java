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

import java.util.HashMap;
import java.util.Map;

/**
 * SAP → 推送PO单 → SRM 处理
 *
 * @author VirtualCry
 */
@JCoFunctionHandler("ZMM_TOSRM_PO2WAN_RFC_INTER")
public class ZMM_TOSRM_PO2WAN_RFC_INTER_CallbackHandler implements FunctionInvokeHandler {

    private static final Log _logger = LogFactory.getLog(ZMM_TOSRM_PO2WAN_RFC_INTER_CallbackHandler.class);

    /* PO单表头字段匹配 - 【SAP - SRM】 */
    private static final Map<String, String> _headerColumnMap = new HashMap<>();
    /* PO单行字段匹配 - 【SAP - SRM】 */
    private static final Map<String, String> _lineColumnMap = new HashMap<>();

    static {
        _headerColumnMap.put("", "");

        _lineColumnMap.put("", "");
    }

    @Override
    public void handleRequest(JCoServerContext _serverCtx, JCoFunction _function) throws AbapException, AbapClassException {

        /* SAP对象 - PO同步到SQL表头：EKKO */
        JCoTable _poFormHeader_Sap = _function.getTableParameterList().getTable("IT_EKKO");
        /* SAP对象 - PO同步到SQL行项目:EKPO */
        JCoTable _poFormLines_Sap = _function.getTableParameterList().getTable("IT_EKPO");
        /* SAP对象 - BAPI返回消息 */
        JCoTable _handlerResult_Sap = _function.getTableParameterList().getTable("IT_RET");

        /* 读取 PO单表头数据 */
        System.out.println();
        System.out.println();
    }
}
