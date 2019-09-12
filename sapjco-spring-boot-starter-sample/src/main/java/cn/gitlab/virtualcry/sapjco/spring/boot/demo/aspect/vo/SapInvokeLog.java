package cn.gitlab.virtualcry.sapjco.spring.boot.demo.aspect.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@Getter @Setter
@Builder
public class SapInvokeLog implements Serializable {

    private String                                  handleId;
    private Date                                    handleTime;
    private AspectHandleType                        handleType;
    private String                                  functionName;
    private Map<String, Object>                     importParameterList;
    private Map<String, Object>                     tableParameterList;
    private Map<String, Object>                     exportParameterList;
    private Map<String, Object>                     changingParameterList;

}
