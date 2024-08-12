# 5.Building Spring Web

## 5.1 Get Started with Spring MVC

### 5.1.1 Following the life of a request

picture

### 5.1.2 Setting up Spring MVC (Configuration)

1. Configuring DispatcherServlet
    - WebApplicationInitializer
    - ServletMapping
    - RootConfig
    - WebConfig

```java
public class SpittrWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
```

2. WebConfig
    - ComponentScan
    - @EnableMvc
    - ViewResolver
    - Configure static content handler

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"cloud.popples.buildweb.spittr.web"})
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }
    
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
```

3. RootConfig
    - ComponentScan

```java
@Configuration
@ComponentScan(basePackages = {"cloud.popples.buildweb.spittr"},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)}
)
public class RootConfig {}
```

## 5.2 Writing a simple controller

```java
@Controller
@RequestMapping(value = {"/", "/homepage"})
public class HomeController {

    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }
}
```

### 5.2.1 Testing controllers


### 5.2.2 Passing model data to the view


### 5.2.3 Testing passing model data


## 5.3 Accepting request input

- Query Parameter
- Form Parameter
- Path Variable

### 5.3.1 Taking query parameters


### 5.3.2 Taking input via path parameters


## 5.4 Processing forms

### 5.4.1 Writing a form-handling controller

### 5.4.2 Validating forms