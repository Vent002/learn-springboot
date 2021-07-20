package cn.hsmxg1204.test;

import cn.hsmxg1204.test.config.dbconfig.MasterDataSourceConfiguration;
import cn.hsmxg1204.test.config.dbconfig.RoutingDataSourceConfiguration;
import cn.hsmxg1204.test.config.dbconfig.SlaveDataSourceConfiguration;
import cn.hsmxg1204.test.config.redis.RedisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * TODO
 *
 * @author gxming
 * @date 2020-12-12 11:46
 */
@SpringBootApplication(scanBasePackages = {
        "cn.hsmxg1204.test",
        "cn.hsmxg1204.core",
        "cn.hsmxg1204.common",
},exclude = DataSourceAutoConfiguration.class)
//@SpringBootApplication(scanBasePackages = {
//        "cn.hsmxg1204.test",
//        "cn.hsmxg1204.core",
//        "cn.hsmxg1204.common",
//},exclude = { DataSourceAutoConfiguration.class })
//@EnableWebMvc//完全接管springboot自动配置
//@MapperScan(basePackages = {"cn.hsmxg1204.core.**.mapper*","cn.hsmxg1204.core.mapper.**"})
@Slf4j
//@EnableFeignClients(basePackages = "cn.hsmxg1204.test.api")
@EnableDiscoveryClient
@Import({MasterDataSourceConfiguration.class, SlaveDataSourceConfiguration.class, RoutingDataSourceConfiguration.class, RedisConfiguration.class})
public class HelloWorldMainApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(HelloWorldMainApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String port = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path");
        String ip = InetAddress.getLocalHost().getHostAddress();
        String name = environment.getProperty("spring.application.name");
        log.info("\n----------------------------------------------------------\n\t"
                + name +" is running! Access URLs:\n\t"
                + "本地访问地址: \thttp://localhost:" + port + path + "\n\t"
                + "接口文档地址: \thttp://" + ip + ":" + port + path +"/swagger-ui.html\n\t"
                + ""
                + "----------------------------------------------------------");
        System.out.println("启动成功");
    }
}
