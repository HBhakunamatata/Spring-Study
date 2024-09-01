package cloud.popples.cachingdata.spittr;

import cloud.popples.cachingdata.spittr.config.RootConfig;
import cloud.popples.cachingdata.spittr.domain.Spittle;
import cloud.popples.cachingdata.spittr.repository.SpittleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@ContextConfiguration(classes = RootConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("prod")
public class SpittleRepositoryTest {

    @Autowired
    private SpittleRepository spittleRepository;

    @Test
    public void count() {
        assertEquals(15, spittleRepository.count());
    }

    @Test
    public void findOne() {
        Spittle thirteen = spittleRepository.findOne(13);
        assertEquals(13, thirteen.getId().longValue());
        assertEquals("Bonjour from Art!", thirteen.getMessage());
        assertEquals(1332653700000L, thirteen.getPostedTime().getTime());
        assertEquals(13, thirteen.getId().longValue());
        assertEquals("artnames", thirteen.getSpitter().getUsername());
        assertEquals("password", thirteen.getSpitter().getPassword());
        assertEquals("Art Names", thirteen.getSpitter().getFullName());
        assertEquals("art@habuma.com", thirteen.getSpitter().getEmail());
        assertTrue(thirteen.getSpitter().isUpdateByEmail());
    }

    @Test
    @Transactional
    public void delete() {
        assertEquals(15, spittleRepository.count());
        assertNotNull(spittleRepository.findOne(13));
        spittleRepository.delete(13L);
        assertEquals(14, spittleRepository.count());
//		assertNull(spittleRepository.findOne(13));
    }

}
