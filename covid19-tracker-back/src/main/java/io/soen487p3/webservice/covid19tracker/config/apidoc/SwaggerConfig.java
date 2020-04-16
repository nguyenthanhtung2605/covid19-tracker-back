package io.soen487p3.webservice.covid19tracker.config.apidoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.soen487p3.webservice.covid19tracker"))
                .paths(regex("/user/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        Contact contact = new Contact("thanh-tung_nguyen",
                "https://github.com/nguyenthanhtung2605", "thomasnguyen265@gmail.com");
        return new ApiInfoBuilder()
                .title("Spring Boot Covid19 Info App Rest API")
                .description("This API will provided online information about where coronavirus is currently active, " +
                        "how many cases in the world, etc")
                .termsOfServiceUrl("https://github.com/nguyenthanhtung2605/covid19-tracker-back")
                .contact(contact)
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
    }
}