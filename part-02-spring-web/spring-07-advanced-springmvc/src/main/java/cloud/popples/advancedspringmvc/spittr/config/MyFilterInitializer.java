package cloud.popples.advancedspringmvc.spittr.config;

import cloud.popples.advancedspringmvc.spittr.servlet.MyFilter;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyFilterInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic myFilter = servletContext.addFilter("MyFilter", new MyFilter());
        myFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
