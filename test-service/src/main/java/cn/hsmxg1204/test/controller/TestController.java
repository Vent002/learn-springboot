package cn.hsmxg1204.test.controller;

import cn.hsmxg1204.test.constant.TreeNodeInit;
import cn.hsmxg1204.test.model.aggregates.TreeRich;
import cn.hsmxg1204.test.model.vo.EngineResult;
import cn.hsmxg1204.test.service.IEngine;
import cn.hsmxg1204.test.service.engine.TreeEngineHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-09-05 15:27
 */
@RestController
public class TestController {

    @Autowired
    TreeNodeInit treeNodeInit;

    @RequestMapping("/test001")
    public EngineResult test(HttpServletRequest request){
        String serverPort = String.valueOf(request.getServerPort());
        if(!serverPort.equals("80") ||!serverPort.equals("443")){
            serverPort ="other";
        }
        String payType ="wx";
        String decodeType="no";
        IEngine treeEngineHandle = new TreeEngineHandle();

        TreeRich treeRich = treeNodeInit.initTreeRich();

        Map<String, String> decisionMatter = new HashMap<>();
        decisionMatter.put("payType", payType);
        decisionMatter.put("decodeType", decodeType);

        decisionMatter.put("port", String.valueOf(serverPort));
        EngineResult result = treeEngineHandle.process(10001L, "Oli09pLkdjh", treeRich, decisionMatter);
        return result;
    }
}
