package cloud.popples.advancedwiring.runtimevalueinjection;

import cloud.popples.advancedwiring.soundsystem.BlankDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:app.properties")
public class ExpressiveConfig {

    @Autowired
    private Environment env;

    @Bean
    public BlankDisc disc() {
        return new BlankDisc(
                env.getProperty("disc.title"),
                env.getProperty("disc.artist")
        );
    }
}
