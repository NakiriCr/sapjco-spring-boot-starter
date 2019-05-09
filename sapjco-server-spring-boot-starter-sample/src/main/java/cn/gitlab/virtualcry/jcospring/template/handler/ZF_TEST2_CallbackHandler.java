package cn.gitlab.virtualcry.jcospring.template.handler;

import cn.gitlab.virtualcry.jcospring.connect.server.handler.FunctionInvokeHandler;
import cn.gitlab.virtualcry.jcospring.connect.util.data.JCoDataUtils;
import cn.gitlab.virtualcry.jcospring.extension.annotation.JCoFunctionHandler;
import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServerContext;

import java.util.Map;

/**
 * Test rfc function handler
 *
 * @author VirtualCry
 */
@JCoFunctionHandler("ZMM_TEST_CONN")
public class ZF_TEST2_CallbackHandler implements FunctionInvokeHandler {

    @Override
    public void handleRequest(JCoServerContext ctx, JCoFunction function)
            throws AbapException, AbapClassException {

        // get sap function parameter value - Import
        Map<String, Object> importParameterList =
                JCoDataUtils.getJCoParameterListValue(function.getImportParameterList());

        // get sap function parameter value - Table
        Map<String, Object> tableParameterList =
                JCoDataUtils.getJCoParameterListValue(function.getTableParameterList());

        // get sap function parameter value - Export
        Map<String, Object> exportParameterList =
                JCoDataUtils.getJCoParameterListValue(function.getChangingParameterList());

        // get sap function parameter value - Changing
        Map<String, Object> changingParameterList =
                JCoDataUtils.getJCoParameterListValue(function.getChangingParameterList());


        /* ============================================================================= */

        // set sap function parameter value - Table
        JCoDataUtils.setJCoParameterListValue(
                function.getTableParameterList(), tableParameterList);

        // set sap function parameter value - Export
        JCoDataUtils.setJCoParameterListValue(
                function.getExportParameterList(), exportParameterList);

    }

}
