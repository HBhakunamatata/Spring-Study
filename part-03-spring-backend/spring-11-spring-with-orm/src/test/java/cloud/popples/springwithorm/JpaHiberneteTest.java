package cloud.popples.springwithorm;


import cloud.popples.springwithorm.spittr.config.PersistenceConfig;
import cloud.popples.springwithorm.spittr.domain.Spitter;
import cloud.popples.springwithorm.spittr.jpahibernete.SpitterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = PersistenceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaHiberneteTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void addUserInfo() {
        Spitter spitter = new Spitter(
                "test-user22", "123456", "FullName", "test@test.com", Boolean.FALSE);
        spitterRepository.addSpitter(spitter);
    }

    @Test
    public void searchUserInfo() {
        Spitter one = spitterRepository.getSpitterById(1L);
        System.out.println(one);
    }

    @Test
    public void updateUserInfo() {
        Spitter spitter = new Spitter();
        spitter.setId(2L);
        spitter.setEmail("test@test.com");
        spitter.setFullName("11name");
        spitter.setUsername("testuser");
        spitter.setPassword("123456");
        spitter.setUpdateByEmail(false);
        spitterRepository.saveSpitter(spitter);
    }

}
