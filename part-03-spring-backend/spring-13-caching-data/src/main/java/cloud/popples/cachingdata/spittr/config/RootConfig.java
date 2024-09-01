package cloud.popples.cachingdata.spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class, RedisCachingConfig.class})
@ComponentScan(basePackages = "cloud.popples.cachingdata.spittr")
public class RootConfig {
}
