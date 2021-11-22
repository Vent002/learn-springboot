package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.model.aggregates.TreeRich;
import cn.hsmxg1204.test.model.vo.EngineResult;

import java.util.Map;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);

}