package cn.hsmxg1204.test.config.dbconfig;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 16:10
 */
public class RoutingDataSourceContext implements AutoCloseable {

    public static final String MASTER_DATASOURCE = "master";
    public static final String SLAVE_DATASOURCE = "slave";

    static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    public static String getDataSourceKey() {
        String key = dataSourceKey.get();
        return key == null?MASTER_DATASOURCE : key;
    }

    public RoutingDataSourceContext(String key){
        dataSourceKey.set(key);
    }

    @Override
    public void close() throws Exception {
        dataSourceKey.remove();
    }
}
