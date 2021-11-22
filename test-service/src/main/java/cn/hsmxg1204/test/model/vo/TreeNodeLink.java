package cn.hsmxg1204.test.model.vo;

import lombok.Data;

@Data
public class TreeNodeLink {

    private Long nodeIdFrom;        //节点From
    private Long nodeIdTo;          //节点To
    private String ruleLimitType;  //限定类型；payType;port;isEncodeType
    private String ruleLimitValue;  //限定值

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public String getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(String ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }
}