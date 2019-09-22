package cn.gitlab.virtualcry.sapjco.spring.boot.demo.endpoint;

import cn.gitlab.virtualcry.sapjco.beans.factory.JCoConnectionFactory;
import cn.gitlab.virtualcry.sapjco.config.JCoSettings;
import cn.gitlab.virtualcry.sapjco.util.data.JCoDataUtils;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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


    @PostMapping("{clientName}/create")
    public void createClient(@PathVariable String clientName, @RequestBody JCoSettings settings) {
        this.connectionFactory.createClient(clientName, settings);
    }

    @GetMapping("{clientName}/settings")
    public JCoSettings getClientSettings(@PathVariable String clientName) {
        return Optional
                .ofNullable(this.connectionFactory.getClient(clientName))
                .orElseThrow(() -> new RuntimeException("Could not find client: [" + clientName + "]"))
                .getSettings();
    }

    @PostMapping("{clientName}/invoke/{functionName}")
    public Map<String, Object> invokeSapFunc(@PathVariable String clientName,
                                             @PathVariable String functionName,
                                             @RequestBody RequestParameter requestParameter) {
        Map<String, Object> invokeResult = new HashMap<>();
        Optional.ofNullable(this.connectionFactory.getClient(clientName))
                .orElseThrow(() -> new RuntimeException("Could not find client: [" + clientName + "]"))
                .invokeSapFunc(
                        functionName,
                        (importParameter, tableParameter, changingParameter) -> {
                            if (requestParameter.getImportParameter() != null)
                                JCoDataUtils.setJCoParameterListValue(importParameter, requestParameter.getImportParameter());
                            if (requestParameter.getImportParameter() != null)
                                JCoDataUtils.setJCoParameterListValue(tableParameter, requestParameter.getTableParameter());
                            if (requestParameter.getImportParameter() != null)
                                JCoDataUtils.setJCoParameterListValue(changingParameter, requestParameter.getChangingParameter());
                        },
                        response -> response.forEach(jCoField -> invokeResult.put(jCoField.getName(), JCoDataUtils.getJCoFieldValue(jCoField)))
                );
        return invokeResult;
    }

    @Data
    private static class RequestParameter {
        private Map<String, Object> importParameter;
        private Map<String, Object> tableParameter;
        private Map<String, Object> changingParameter;
    }
}
