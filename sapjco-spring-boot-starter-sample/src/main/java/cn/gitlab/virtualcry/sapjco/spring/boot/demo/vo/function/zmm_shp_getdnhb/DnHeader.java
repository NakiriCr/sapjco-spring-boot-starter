package cn.gitlab.virtualcry.sapjco.spring.boot.demo.vo.function.zmm_shp_getdnhb;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

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
public class DnHeader {

    @JSONField(name = "VBELN")
    private String dnNo;

}
