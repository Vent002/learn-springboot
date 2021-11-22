package cn.hsmxg1204.test.service;

import cn.hsmxg1204.test.service.filter.DecodeFilter;
import cn.hsmxg1204.test.service.filter.PayTypeFilter;
import cn.hsmxg1204.test.service.filter.PortFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EngineConfig {

    static Map<String, LogicFilter> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put("payType", new PayTypeFilter());
        logicFilterMap.put("decodeType", new DecodeFilter());
        logicFilterMap.put("port", new PortFilter());
    }

    public static Map<String, LogicFilter> getLogicFilterMap() {
        return logicFilterMap;
    }

    public void setLogicFilterMap(Map<String, LogicFilter> logicFilterMap) {
        this.logicFilterMap = logicFilterMap;
    }

}