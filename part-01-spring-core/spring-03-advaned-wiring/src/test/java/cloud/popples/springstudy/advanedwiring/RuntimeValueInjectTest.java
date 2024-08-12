package cloud.popples.springstudy.advanedwiring;

import advancedwiring.runtimevalueinjection.ExpressiveConfig;
import advancedwiring.soundsystem.BlankDisc;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ExpressiveConfig.class})
public class RuntimeValueInjectTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Autowired
    private BlankDisc blankDisc;

    @Test
    public void testPropertySourceValue() {
        Assert.assertNotNull(blankDisc);
        blankDisc.play();
        Assert.assertEquals(
                "Playing Sgt. Pepper\'s Lonely Hearts Club Band by The Beatles",
                log.getLog().trim());
    }
}
