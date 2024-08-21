package cloud.popples.securingweb.spittr.config;

import cloud.popples.securingweb.spittr.servlet.MyServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MyServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("MyServlet", new MyServlet());
        myServlet.addMapping("/myservlet");
    }
}
