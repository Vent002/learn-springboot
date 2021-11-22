package cn.hsmxg1204.test.service.engine;

import cn.hsmxg1204.test.model.aggregates.TreeRich;
import cn.hsmxg1204.test.model.vo.EngineResult;
import cn.hsmxg1204.test.model.vo.TreeNode;
import cn.hsmxg1204.test.service.EngineBase;

import java.util.Map;

public class TreeEngineHandle extends EngineBase {

    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        // 决策流程
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);
        // 决策结果
        return new EngineResult(userId, treeId, treeNode.getTreeNodeId(), treeNode.getNodeValue());
    }

}
