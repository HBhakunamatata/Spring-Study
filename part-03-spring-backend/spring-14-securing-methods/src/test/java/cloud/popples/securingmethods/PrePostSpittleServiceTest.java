package cloud.popples.securingmethods;

import cloud.popples.securingmethods.spittr.config.PrePostSecuredConfig;
import cloud.popples.securingmethods.spittr.domain.Spitter;
import cloud.popples.securingmethods.spittr.domain.Spittle;
import cloud.popples.securingmethods.spittr.service.SpittleService;
import org.junit.Assert;
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

@ContextConfiguration(classes = PrePostSecuredConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PrePostSpittleServiceTest {

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
        setupUser("ROLE_SPITTER");
//        setupUser("ROLE_ADMIN");

        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "craig@habuma.com", true);
        Spittle spittle = new Spittle(1L, spitter, "", new Date());
        spittleService.addSpittle(spittle);
    }


    @Test
    public void testAuthenticatedMethod_ownSpittle() {
        setupUser("ROLE_SPITTER");

        Spittle spittle = spittleService.getSpittleById(1L);
        Assert.assertNotNull(spittle);
    }


    @Test
    public void testPostFilteredMethod_ownSpittle() {
        setupUser("ROLE_SPITTER");

        List<Spittle> spittles = spittleService.getAllSpittles();
        Assert.assertEquals(3, spittles.size());
    }

    @Test
    public void testPreFilteredMethod_ownSpittle() {
        setupUser("ROLE_SPITTER");

        List<Spittle> spittles = new ArrayList<Spittle>();

        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setUsername("user");
        spitter.setPassword("password");

        Spitter admin = new Spitter();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("password");

        for (int i = 0; i < 3; i++) {
            Spittle spittle = new Spittle();
            spittle.setId((long)i);
            spittle.setMessage("Hello World");
            spittle.setPostedTime(new Date());
            spittle.setSpitter(spitter);

            spittles.add(spittle);

            Spittle spittle2 = new Spittle();
            spittle2.setId((long)i);
            spittle2.setMessage("Hello World");
            spittle2.setPostedTime(new Date());
            spittle2.setSpitter(admin);

            spittles.add(spittle2);
        }

        spittleService.deleteSpittles(spittles);
    }

    @Test
    public void testPermissionMethod_ownSpittle() {
        setupUser("ROLE_SPITTER");

        List<Spittle> spittles = new ArrayList<Spittle>();

        Spitter spitter = new Spitter();
        spitter.setId(1L);
        spitter.setUsername("user");
        spitter.setPassword("password");

        Spitter admin = new Spitter();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("password");

        for (int i = 0; i < 3; i++) {
            Spittle spittle = new Spittle();
            spittle.setId((long)i);
            spittle.setMessage("Hello World");
            spittle.setPostedTime(new Date());
            spittle.setSpitter(spitter);

            spittles.add(spittle);

            Spittle spittle2 = new Spittle();
            spittle2.setId((long)i);
            spittle2.setMessage("Hello World");
            spittle2.setPostedTime(new Date());
            spittle2.setSpitter(admin);

            spittles.add(spittle2);
        }

        int i = spittleService.updateSpittle(spittles);
        Assert.assertEquals(3, i);
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
