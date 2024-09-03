package cloud.popples.securingmethods.spittr.config;

import cloud.popples.securingmethods.spittr.service.SpittleService;
import cloud.popples.securingmethods.spittr.service.impl.JSR250SpittleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class JSR250SecureConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN")
                .and()
                .withUser("user").password("password").roles("SPITTER");
    }

    @Bean
    public SpittleService securedService() {
        return new JSR250SpittleService();
    }
}
