package cloud.popples.swagger.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableSwaggerBootstrapUI
@ConditionalOnProperty(value = {"swagger2.enable"}, matchIfMissing = true)
public class SwaggerConfig {

    private boolean swagger2Enable;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // bind display information in swagger-ui
            .enable(true)
            .apiInfo(apiInfo())
            .select()
                // scan the controllers under the path
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("cloud.popples.swagger.controller")))
            .build();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo project for swagger")
                .description("Description for this project")
                .version("1.0.0")
                .build();
    }

}
