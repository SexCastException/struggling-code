package com.huazai.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 配置api接口信息
                .apiInfo(apiInfo())

                .select()
                // 1.1、RequestHandlerSelectors.basePackage("")指定扫描哪个包下面的接口，默认是any()
                // 1.2、RequestHandlerSelectors.any()扫描全部
                // 1.3、RequestHandlerSelectors.none()不扫描
                // 1.4、RequestHandlerSelectors.withClassAnnotation(RestController.class)扫描带有RestController注解的类
                // 1.5、RequestHandlerSelectors.withMethodAnnotation(GetMapping.class)扫描带有GetMapping注解的方法
                .apis(RequestHandlerSelectors.basePackage("com.huazai.swagger.controller"))
                // 2.1、PathSelectors.ant("")指定过滤哪个访问路径
                // 2.2、PathSelectors.any()过滤全部访问路径，默认
                // 2.3、PathSelectors.none()不过滤访问路径
                // 2.4、PathSelectors.regex("")使用正则表达式过滤访问路径
                .paths(PathSelectors.ant("/hello"))
                .build()
                ;
    }

    /**
     * 不配置此Bean则默认使用ApiInfo.DEFAULT信息
     * @return
     */
    @Bean
    public ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("庞英华", "https://github.com/SexCastException/JVM", "1908421836@qq.com");
        return new ApiInfo("这是我的第一个swagger项目", "这个项目很好，使用这个框架的我也很帅", "1.0.0",
                "华仔战队", contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());   // 额外的信息保存在该List里
    }
}
