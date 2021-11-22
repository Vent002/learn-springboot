package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.model.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

public abstract class BaseLogic implements LogicFilter {

    @Override
    public Long filter(String matterValue, List<TreeNodeLink> treeNodeLinkList) {
        for (TreeNodeLink nodeLine : treeNodeLinkList) {
            if (decisionLogic(matterValue, nodeLine)) return nodeLine.getNodeIdTo();
        }
        return 0L;
    }

    @Override
    public abstract String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);

    private boolean decisionLogic(String matterValue, TreeNodeLink nodeLink) {
        switch (nodeLink.getRuleLimitType()) {
            case "port":
                return matterValue.equals(nodeLink.getRuleLimitValue());
            case "decodeType":
                return matterValue.equals(nodeLink.getRuleLimitValue());
            case "payType":
                return matterValue.equals(nodeLink.getRuleLimitValue());
            default:
                return false;
        }
    }

}