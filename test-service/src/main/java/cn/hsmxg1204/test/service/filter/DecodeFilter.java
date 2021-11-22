package cn.hsmxg1204.test.service.filter;

import cn.hsmxg1204.test.service.BaseLogic;

import java.util.Map;

public class DecodeFilter extends BaseLogic {

    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("decodeType");
    }

}