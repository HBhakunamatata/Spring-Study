package cloud.popples.advancedwiring.runtimevalueinjection;


import cloud.popples.advancedwiring.soundsystem.BlankDisc2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackageClasses = BlankDisc2.class)
@PropertySource("classpath:app.properties")
public class PlaceholderConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
