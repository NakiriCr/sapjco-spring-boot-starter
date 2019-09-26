package cn.gitlab.virtualcry.sapjco.spring.boot.demo.vo.function.zmm_shp_getdnhb;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Somethings
 *
 * @author VirtualCry
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableParameter {

    @JSONField(name = "IT_DNHB")
    private List<DnHeader> dnHeaders;

    @JSONField(name = "OT_PRODUCT")
    private List<Map<String, Object>> dnDetails;

}
