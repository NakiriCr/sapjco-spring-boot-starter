package cn.gitlab.virtualcry.sapjco.spring.boot.demo.aspect.vo;

/**
 * Somethings
 *
 * @author VirtualCry
 */
public enum AspectHandleType {

    BEFORE("BEFORE"), AFTER("AFTER");

    AspectHandleType(String itemValue) {
        this.itemValue = itemValue;
    }
    private String itemValue;
    public String getItemValue() {
        return itemValue;
    }

}
