package cn.hsmxg1204.test.service;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 17:20
 */
public interface Buyer {
    /**
     * 计算应付价格
     */
    BigDecimal calPrice(BigDecimal orderPrice);

}
