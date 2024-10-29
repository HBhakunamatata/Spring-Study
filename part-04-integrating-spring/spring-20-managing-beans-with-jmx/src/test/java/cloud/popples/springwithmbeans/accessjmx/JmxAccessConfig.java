package cloud.popples.springwithmbeans.accessjmx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import java.net.MalformedURLException;

@Configuration
public class JmxAccessConfig {

    @Bean
    public MBeanServerConnectionFactoryBean connectionFactory() throws MalformedURLException {
        MBeanServerConnectionFactoryBean bean = new MBeanServerConnectionFactoryBean();
        bean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/spitter");
        return bean;
    }

}
