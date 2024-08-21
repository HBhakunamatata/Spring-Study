package cloud.popples.securingweb.spittr.config;

import cloud.popples.securingweb.spittr.repository.SpitterRepository;
import cloud.popples.securingweb.spittr.service.SpitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SpitterRepository repository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/spitter/**").authenticated()
                .regexMatchers("/spittles/.*").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .requiresChannel().antMatchers(HttpMethod.POST, "/spittles/**").requiresSecure()
                .and()
                .httpBasic().realmName("Spittr")
                .and()
                .rememberMe().tokenValiditySeconds(24 * 60 * 60).key("spitterKey")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/")
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("123456").roles("USER")
                .and()
                .withUser("admin").password("123456").roles("ADMIN", "USER");
    }


    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, true from spitter where username = ?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from spitter where username = ?")
////                .passwordEncoder(new StandardPasswordEncoder())
//        ;
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new SpitterUserService(repository));
//    }
}
