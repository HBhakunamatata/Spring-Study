package cloud.popples.workwithremote.spittr.config;

import cloud.popples.workwithremote.spittr.service.SpitterService;
import cloud.popples.workwithremote.spittr.service.SpitterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
public class HttpInvokeServerConfig {

    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter(SpitterService spitterService) {
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setServiceInterface(SpitterService.class);
        httpInvokerServiceExporter.setService(spitterService);
        return httpInvokerServiceExporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping() {
        SimpleUrlHandlerMapping httpInvokerMapping = new SimpleUrlHandlerMapping();
        Properties properties = new Properties();
        properties.setProperty("/spitter.service", "httpInvokerServiceExporter");
        httpInvokerMapping.setMappings(properties);
        return httpInvokerMapping;
    }

    @Bean
    public SpitterService spitterService() {
        return new SpitterServiceImpl();
    }
}
