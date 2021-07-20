package cn.hsmxg1204.test.entity;

import cn.hsmxg1204.test.constant.Part;
import lombok.Data;

import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-05-17 15:21
 */
@Data
public class PartContent {
    private Part part;

    private String rawContent;

    private Map<String, Object> pairs;
}
