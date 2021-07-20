package cn.hsmxg1204.test.config.dbconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 16:19
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return RoutingDataSourceContext.getDataSourceKey();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        DataSource ds = super.determineTargetDataSource();
        log.info("determin target datasource: {}",ds);
        return ds;
    }
}
