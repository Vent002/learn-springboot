package cn.hsmxg1204.test.constant;

import cn.hsmxg1204.test.model.aggregates.TreeRich;
import cn.hsmxg1204.test.model.vo.TreeNode;
import cn.hsmxg1204.test.model.vo.TreeNodeLink;
import cn.hsmxg1204.test.model.vo.TreeRoot;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-09-05 15:50
 */
@Component
public class TreeNodeInit {

    @Bean
    public TreeRich initTreeRich(){

        // 树根
        TreeRoot treeRoot = new TreeRoot();
        treeRoot.setTreeId(10001L);
        treeRoot.setTreeRootNodeId(1L);
        treeRoot.setTreeName("规则决策树");

        TreeNode treeNode_01 = new TreeNode();
        treeNode_01.setTreeId(10001L);
        treeNode_01.setTreeNodeId(1L);
        treeNode_01.setNodeType(1);
        treeNode_01.setNodeValue(null);
        treeNode_01.setRuleKey("port");
        treeNode_01.setRuleDesc("端口号");

        // 链接：1->11
        TreeNodeLink treeNodeLink_11 = new TreeNodeLink();
        treeNodeLink_11.setNodeIdFrom(1L);
        treeNodeLink_11.setNodeIdTo(11L);
        treeNodeLink_11.setRuleLimitType("port");
        treeNodeLink_11.setRuleLimitValue("80");

        // 链接：1->12
        TreeNodeLink treeNodeLink_12 = new TreeNodeLink();
        treeNodeLink_12.setNodeIdFrom(1L);
        treeNodeLink_12.setNodeIdTo(12L);
        treeNodeLink_12.setRuleLimitType("port");
        treeNodeLink_12.setRuleLimitValue("443");

        // 链接：1->13
        TreeNodeLink treeNodeLink_13 = new TreeNodeLink();
        treeNodeLink_13.setNodeIdFrom(1L);
        treeNodeLink_13.setNodeIdTo(13L);
        treeNodeLink_13.setRuleLimitType("port");
        treeNodeLink_13.setRuleLimitValue("other");

        List<TreeNodeLink> treeNodeLinkList_1 = new ArrayList<>();
        treeNodeLinkList_1.add(treeNodeLink_11);
        treeNodeLinkList_1.add(treeNodeLink_12);
        treeNodeLinkList_1.add(treeNodeLink_13);

        treeNode_01.setTreeNodeLinkList(treeNodeLinkList_1);


        TreeNode treeNode_02 = new TreeNode();
        treeNode_02.setTreeId(10002L);
        treeNode_02.setTreeNodeId(2L);
        treeNode_02.setNodeType(1);
        treeNode_02.setNodeValue(null);
        treeNode_02.setRuleKey("decodeType");
        treeNode_02.setRuleDesc("加解密");

        // 链接：2->21
        TreeNodeLink treeNodeLink_21 = new TreeNodeLink();
        treeNodeLink_21.setNodeIdFrom(2L);
        treeNodeLink_21.setNodeIdTo(21L);
        treeNodeLink_21.setRuleLimitType("decodeType");
        treeNodeLink_21.setRuleLimitValue("1");

        // 链接：2->22
        TreeNodeLink treeNodeLink_22 = new TreeNodeLink();
        treeNodeLink_22.setNodeIdFrom(2L);
        treeNodeLink_22.setNodeIdTo(22L);
        treeNodeLink_21.setRuleLimitType("decodeType");
        treeNodeLink_22.setRuleLimitValue("0");

        List<TreeNodeLink> treeNodeLinkList_2 = new ArrayList<>();
        treeNodeLinkList_2.add(treeNodeLink_21);
        treeNodeLinkList_2.add(treeNodeLink_22);

        treeNode_02.setTreeNodeLinkList(treeNodeLinkList_2);

        TreeNode treeNode_03 = new TreeNode();
        treeNode_03.setTreeId(10003L);
        treeNode_03.setTreeNodeId(3L);
        treeNode_03.setNodeType(1);
        treeNode_03.setNodeValue(null);
        treeNode_03.setRuleKey("payType");
        treeNode_03.setRuleDesc("加解密");

        // 链接：2->21
        TreeNodeLink treeNodeLink_31 = new TreeNodeLink();
        treeNodeLink_31.setNodeIdFrom(3L);
        treeNodeLink_31.setNodeIdTo(31L);
        treeNodeLink_31.setRuleLimitType("payType");
        treeNodeLink_31.setRuleLimitValue("wx");

        // 链接：2->22
        TreeNodeLink treeNodeLink_32 = new TreeNodeLink();
        treeNodeLink_32.setNodeIdFrom(3L);
        treeNodeLink_32.setNodeIdTo(32L);
        treeNodeLink_32.setRuleLimitType("payType");
        treeNodeLink_32.setRuleLimitValue("ali");

        List<TreeNodeLink> treeNodeLinkList_3 = new ArrayList<>();
        treeNodeLinkList_3.add(treeNodeLink_31);
        treeNodeLinkList_3.add(treeNodeLink_32);

        treeNode_03.setTreeNodeLinkList(treeNodeLinkList_3);


        Map<Long, TreeNode> treeNodeMap = new HashMap<>();
        treeNodeMap.put(1L, treeNode_01);
        treeNodeMap.put(2L, treeNode_02);

        treeNodeMap.put(3L, treeNode_03);

        return new TreeRich(treeRoot, treeNodeMap);
    }
}
