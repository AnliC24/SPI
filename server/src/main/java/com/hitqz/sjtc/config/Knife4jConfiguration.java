package com.hitqz.sjtc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author windc
 * @description 2022.3.8 引入springfox  3.0.0 修改
 * 2022.4.11 引入knife4j,统一api格式
 */
@Configuration
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("烧结台车项目接口文档")
                        .description("烧结台车项目接口文档")
                        .contact(new Contact("hitqz","",""))
                        .termsOfServiceUrl("工研院-智能技术中心")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.2.x")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hitqz.sjtc.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


}
