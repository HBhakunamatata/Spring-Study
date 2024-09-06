package cloud.popples.workwithremote.jaxws;

import cloud.popples.workwithremote.spittr.domain.Spitter;
import cloud.popples.workwithremote.spittr.service.SpitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = JaxwsClientConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JaxwsServiceTest {

    @Autowired
    private SpitterService spitterService;

    @Test
    public void test() {
        Spitter spitter = spitterService.getSpitterById(1);
    }

}
