package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.test.service.Buyer;
import cn.hsmxg1204.test.strategy.UserPayServiceStrategyFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 17:20
 */
@Service
public class BuyerStrategyImpl implements Buyer {
    @Override
    public BigDecimal calPrice(BigDecimal orderPrice) {
        if(orderPrice.compareTo(new BigDecimal(30)) > 0) {
            return orderPrice.multiply(new BigDecimal(0.8));
        }
        return orderPrice;
    }
}

@Service
class SuperVipUser implements Buyer {

    @Override
    public BigDecimal calPrice(BigDecimal orderPrice) {
        return orderPrice.multiply(new BigDecimal(0.75));
    }
}

@Service
class VipUser implements Buyer, InitializingBean {

    @Override
    public BigDecimal calPrice(BigDecimal orderPrice) {
        return orderPrice.multiply(new BigDecimal(0.88));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory factory = new UserPayServiceStrategyFactory();
    }
}