package cloud.popples.springwithmbeans.config;

import cloud.popples.springwithmbeans.web.SpittleController;
import cloud.popples.springwithmbeans.web.SpittleControllerManagedOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodExclusionMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmxConfig {

    @Autowired
    private SpittleController spittleController;

    @Bean
    public MBeanExporter exporter() {
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("spitter:name=SpittleController", spittleController);
        exporter.setBeans(beans);
        exporter.setAssembler(mBeanInfoAssembler());
        exporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return exporter;
    }

//    @Bean
//    public MethodNameBasedMBeanInfoAssembler mBeanInfoAssembler() {
//        MethodNameBasedMBeanInfoAssembler mBeanInfoAssembler = new MethodNameBasedMBeanInfoAssembler();
//        mBeanInfoAssembler.setManagedMethods(
//                "getSpittlesPerPage", "setSpittlesPerPage"
//        );
//        return mBeanInfoAssembler;
//    }

//    @Bean
//    public MethodExclusionMBeanInfoAssembler exclusionMBeanInfoAssembler () {
//        MethodExclusionMBeanInfoAssembler exclusionMBeanInfoAssembler = new MethodExclusionMBeanInfoAssembler();
//        exclusionMBeanInfoAssembler.setIgnoredMethods("spittles");
//        return exclusionMBeanInfoAssembler;
//    }

    @Bean
    public InterfaceBasedMBeanInfoAssembler mBeanInfoAssembler() {
        InterfaceBasedMBeanInfoAssembler mBeanInfoAssembler = new InterfaceBasedMBeanInfoAssembler();
        mBeanInfoAssembler.setManagedInterfaces(SpittleControllerManagedOperation.class);
        return mBeanInfoAssembler;
    }


    // Remote JMX

    @Bean
    public ConnectorServerFactoryBean connectorServer() {
        ConnectorServerFactoryBean connectorServer = new ConnectorServerFactoryBean();
        connectorServer.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/spitter");
        return connectorServer;
    }

    @Bean
    public RmiRegistryFactoryBean rmiRegistryFactoryBean() {
        RmiRegistryFactoryBean registry = new RmiRegistryFactoryBean();
        registry.setPort(1099);
        return registry;
    }

}
