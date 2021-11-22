package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.model.aggregates.TreeRich;
import cn.hsmxg1204.test.model.vo.EngineResult;
import cn.hsmxg1204.test.model.vo.TreeNode;
import cn.hsmxg1204.test.model.vo.TreeRoot;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.EngineConfig;

import java.util.Map;

@Slf4j
public abstract class EngineBase extends EngineConfig implements IEngine {

    @Override
    public abstract EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter);

    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId, String userId, Map<String, String> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNodeInfo = treeNodeMap.get(rootNodeId);
        //节点类型[NodeType]；1子叶、2果实
        while (treeNodeInfo.getNodeType().equals(1)) {
            String ruleKey = treeNodeInfo.getRuleKey();

            Map<String, LogicFilter> logicFilterMap = cn.hsmxg1204.test.service.EngineConfig.getLogicFilterMap();

            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(treeId, userId, decisionMatter);
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLinkList());
            treeNodeInfo = treeNodeMap.get(nextNode);
        }
        return treeNodeInfo;
    }

}