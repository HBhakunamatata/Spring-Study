package cloud.popples.aop.concert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @author: hb
 * @date: 2023/05/13 17:25
 */

@Configuration
@EnableAspectJAutoProxy
public class EncoreableConfig {
    
    @Bean
    public EncoreableIntroducer encoreableIntroducer() {
        return new EncoreableIntroducer();
    }

    @Bean
    public Encoreable encoreable() {
        return new DefaultEncoreable();
    }

//    @Bean
//    public Performance performance() {
//        return new JackConcert();
//    }
}
