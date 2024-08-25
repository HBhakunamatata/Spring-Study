package cloud.popples.databaseandjdbc;

import cloud.popples.databaseandjdbc.config.DataSourceConfig;
import cloud.popples.databaseandjdbc.pojo.Spitter;
import cloud.popples.databaseandjdbc.repository.SpitterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = DataSourceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("default")
public class JdbcTemplateTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void addUserInfo() {
        Spitter spitter = new Spitter();
        spitter.setEmail("test@test.com");
        spitter.setFirstName("Firstname");
        spitter.setLastName("Lastname");
        spitter.setUsername("test-user");
        spitter.setPassword("123456");
        spitterRepository.addSpitter(spitter);
    }

    @Test
    public void searchUserInfo() {
        Spitter one = spitterRepository.findOne(1L);
        System.out.println(one);
    }

    @Test
    public void updateUserInfo() {
        Spitter spitter = new Spitter();
        spitter.setId(2L);
        spitter.setEmail("test@test.com");
        spitter.setFirstName("11name");
        spitter.setLastName("22name");
        spitter.setUsername("testuser");
        spitter.setPassword("123456");
        spitterRepository.updateSpitter(spitter);
    }

}
