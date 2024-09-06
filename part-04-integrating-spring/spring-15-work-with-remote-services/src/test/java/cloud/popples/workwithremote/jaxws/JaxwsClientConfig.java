package cloud.popples.workwithremote.jaxws;

import cloud.popples.workwithremote.spittr.service.SpitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class JaxwsClientConfig {
    @Bean
    public JaxWsPortProxyFactoryBean portProxyFactory() throws MalformedURLException {
        JaxWsPortProxyFactoryBean proxyFactory = new JaxWsPortProxyFactoryBean();
        proxyFactory.setWsdlDocumentUrl(new URL("http://localhost:8080/services/SpitterService?wsdl"));
        proxyFactory.setPortName("spitterServicePort");
        proxyFactory.setServiceName("spitterService");
        proxyFactory.setServiceInterface(SpitterService.class);
        proxyFactory.setNamespaceUri("http://spitter.com");
        return proxyFactory;
    }
}
