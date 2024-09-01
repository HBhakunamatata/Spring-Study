package cloud.popples.springwithorm;

import cloud.popples.springwithorm.spittr.config.PersistenceConfig;
import cloud.popples.springwithorm.spittr.domain.Spitter;
import cloud.popples.springwithorm.spittr.jpaspringdata.SpitterRepository2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(classes = PersistenceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaSpringDataTest {

    @Autowired
    private SpitterRepository2 spitterRepository;

    @Test
    @Transactional
    public void count() {
        Assert.assertEquals(3, spitterRepository.count());
    }

    @Test
    public void testGetAllGmailSpitter() {
        List<Spitter> allGmailSpitters = spitterRepository.findAllGmailSpitters();
        Assert.assertEquals(1, allGmailSpitters.size());
    }

    @Test
    public void testSweepSpitter() {
        Assert.assertEquals(1, spitterRepository.eliteSweep());
    }

}
