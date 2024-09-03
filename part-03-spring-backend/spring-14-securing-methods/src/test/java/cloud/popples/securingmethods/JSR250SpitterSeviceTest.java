package cloud.popples.securingmethods;

import cloud.popples.securingmethods.spittr.config.JSR250SecureConfig;
import cloud.popples.securingmethods.spittr.domain.Spitter;
import cloud.popples.securingmethods.spittr.domain.Spittle;
import cloud.popples.securingmethods.spittr.service.SpittleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = JSR250SecureConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JSR250SpitterSeviceTest {

    @Autowired
    private SpittleService spittleService;

    @Before
    public void cleanContext() {
        SecurityContextHolder.clearContext();
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testSecuredMethod_noCredentials() {
        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "craig@habuma.com", true);
        Spittle spittle = new Spittle(1L, spitter, "", new Date());
        spittleService.addSpittle(spittle);
    }

    @Test
    public void testSecuredMethod_withCredentials() {
//        setupUser("ROLE_SPITTER");
        setupUser("ROLE_ADMIN");

        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "craig@habuma.com", true);
        Spittle spittle = new Spittle(1L, spitter, "", new Date());
        spittleService.addSpittle(spittle);
    }


    private void setupUser(String ... privs) {
        SecurityContext context = SecurityContextHolder.getContext();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String priv : privs) {
            authorities.add(new SimpleGrantedAuthority(priv));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "password", authorities);
        context.setAuthentication(authenticationToken);
    }

}
