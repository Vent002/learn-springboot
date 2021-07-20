package cn.hsmxg1204.test.config.dbconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 16:17
 */
public class RoutingDataSourceConfiguration {

    @Bean
    DataSource dataSource(@Autowired @Qualifier(RoutingDataSourceContext.MASTER_DATASOURCE) DataSource masterDataSource,
                          @Autowired @Qualifier(RoutingDataSourceContext.SLAVE_DATASOURCE)DataSource slaveDataSource){
        RoutingDataSource dataSource = new RoutingDataSource();
        Map<Object,Object> map = new HashMap<>(2);
        map.put(RoutingDataSourceContext.MASTER_DATASOURCE,masterDataSource);

        map.put(RoutingDataSourceContext.SLAVE_DATASOURCE,slaveDataSource);

        dataSource.setTargetDataSources(map);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
