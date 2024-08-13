# 7. Advanced Spring MVC

- Alternative Spring MVC configuration options
- Handle file uploads
- Handle exceptions in controllers
- Working with flash attributes

## 7.1 Alternate Spring MVC configuration

## 7.1.1 Customizing DispatcherServlet configuration

Method1 : customizeRegistration(Dynamic registration)

```java
import javax.servlet.MultipartConfigElement;

@Override
protected void customizeRegistration(Dynamic registration) {
    registration.setMultiPartConfig(
            new MultipartConfigElement("/tmp/spittr/uploads")
    );
}
```

### 7.1.2 Adding additional servlets and filters

If you need to register any additional components into the web container, you need only create a new initializer class. The easiest way to do this is by implementing Spring’s WebApplicationInitializer interface.

```java
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.Registration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Registration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        myServlet.addMapping("/custom/**");
    }
}

```

Two methods to add Filters

```java
public class MyFilterInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Registration.Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
        myFilter.addMappingForUrlPatterns(null, false, "custom/*");
    }
}
```

To register one or more filters and map them to DispatcherServlet, all you need to do is override the getServletFilters() method of AbstractAnnotationConfigDispatcherServletInitializer.

```java
@Override
protected Filter [] getServletFilters() {
    return new Filter [] {
            new MyFilter()
    };
}
```