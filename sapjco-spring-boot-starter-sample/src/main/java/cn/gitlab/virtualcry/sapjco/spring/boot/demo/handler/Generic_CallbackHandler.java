package cn.gitlab.virtualcry.sapjco.spring.boot.demo.handler;

import cn.gitlab.virtualcry.sapjco.server.handler.FunctionInvokeHandler;
import cn.gitlab.virtualcry.sapjco.spring.annotation.JCoFunctionHandler;
import cn.gitlab.virtualcry.sapjco.util.data.JCoDataUtils;
import com.google.common.collect.ImmutableMap;
import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServerContext;

import java.util.Collections;
import java.util.Map;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@JCoFunctionHandler("GENERIC_HANDLER")
public class Generic_CallbackHandler implements FunctionInvokeHandler {

    @Override
    public void handleRequest(JCoServerContext jCoServerContext, JCoFunction jCoFunction) throws AbapException, AbapClassException {

        Map<String, Object> table = JCoDataUtils.getJCoParameterListValue(jCoFunction.getTableParameterList());
        table.put("IT_RET", Collections.singletonList(
                ImmutableMap.<String, Object>builder()
                        .put("ZZQKDH", "")          /* 【CHAR】物料凭证编号 */
                        .put("ZZQKDH_ND", "")       /* 【NUM】物料凭证年度 */
                        .put("ZZFLAG", "S")          /* 【CHAR】成功标识 - S 表示成功，X表示失败 */
                        .put("ZZMESSAGE", "处理成功")       /* 【CHAR 文本字段长度 200】原因  */
                        .put("ZZDJH", "")           /* 【CHAR 字符字段，长度 32】单据号 */
                        .put("ZZCOUNT", "")         /* 【INT4】数据行数 */
                        .put("ZZSOURCEID", "")      /* 【CHAR】包的请求单据号 */
                        .build()
                )
        );
        JCoDataUtils.setJCoParameterListValue(jCoFunction.getTableParameterList(), table);

    }

}
