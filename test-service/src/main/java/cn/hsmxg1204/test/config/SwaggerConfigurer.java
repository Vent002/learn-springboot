package cn.hsmxg1204.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 13:21
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "cn.hsmxg1204.test.controller")
public class SwaggerConfigurer {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("hsmxg")
                .enable(true)
                .globalOperationParameters(setGlobalOperationParameter())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.hsmxg1204.test.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    // 创建api的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试")
                .description("springboot+mybatis+redis")
                .termsOfServiceUrl("https://www.hsmxg1204.cn")
                .version("1.0")
                .build();

    }
    private List<Parameter> setGlobalOperationParameter(){
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(true);
        parameters.add(parameterBuilder.build());
        return parameters;
    }
}
