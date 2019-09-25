package cn.gitlab.virtualcry.sapjco.spring.boot.demo.endpoint;

import cn.gitlab.virtualcry.sapjco.beans.factory.JCoConnectionFactory;
import cn.gitlab.virtualcry.sapjco.client.JCoClient;
import cn.gitlab.virtualcry.sapjco.client.handler.FunctionRequestHandler;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import cn.gitlab.virtualcry.sapjco.spring.boot.demo.vo.function.zmm_shp_getdnhb.DnHeader;
import cn.gitlab.virtualcry.sapjco.spring.boot.demo.vo.function.zmm_shp_getdnhb.TableParameter;
import cn.gitlab.virtualcry.sapjco.util.data.JCoDataUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@RestController
@RequestMapping("client")
public class ClientEndpoint {

    @Resource
    private JCoConnectionFactory        connectionFactory;

    @GetMapping("names")
    public Collection<String> getAllClientNames() {
        return connectionFactory.getClients().keySet();
    }

    @GetMapping("invoke")
    public Map<String, Object> invokeSapFunc() {
        JCoClient client = connectionFactory.getClient("main-client");
        String functionName = "ZMM_SHP_GETDNHB";
        FunctionRequestHandler requestHandler = (importParameter, tableParameter, changingParameter) -> {
            TableParameter tableParameterValue = TableParameter.builder()
                    .dnHeaders(Collections.singletonList(DnHeader.builder().dnNo("0080055489").build()))
                    .build();
            JCoDataUtils.setJCoParameterListValue(tableParameter, tableParameterValue);
        };
        return client.invokeSapFunc(functionName, requestHandler);
    }

    @GetMapping("invokeToBean")
    public TableParameter invokeSapFuncToBean() {
        JCoClient client = connectionFactory.getClient("main-client");
        String functionName = "ZMM_SHP_GETDNHB";
        FunctionRequestHandler requestHandler = (importParameter, tableParameter, changingParameter) -> {
            TableParameter tableParameterValue = TableParameter.builder()
                    .dnHeaders(Collections.singletonList(DnHeader.builder().dnNo("0080055489").build()))
                    .build();
            JCoDataUtils.setJCoParameterListValue(tableParameter, tableParameterValue);
        };
        return client.invokeSapFunc(functionName, requestHandler, TableParameter.class);
    }

    @GetMapping("{clientName}/settings")
    public JCoSettings getServerSettings(@PathVariable String clientName) {
        return Optional
                .ofNullable(this.connectionFactory.getClient(clientName))
                .orElseThrow(() -> new RuntimeException("Could not find client: [" + clientName + "]"))
                .getSettings();
    }
}
