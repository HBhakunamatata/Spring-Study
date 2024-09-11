package cloud.popples.advanedwiring;

import cloud.popples.advancedwiring.runtimevalueinjection.PlaceholderConfig;
import cloud.popples.advancedwiring.soundsystem.BlankDisc2;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PlaceholderConfig.class)
public class RuntimeValueInjectTest2 {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Autowired
    private BlankDisc2 blankDisc;

    @Test
    public void testBlankDisc() {
        Assert.assertNotNull(blankDisc);
        blankDisc.play();
        Assert.assertEquals(
                "Playing Sgt. Pepper\'s Lonely Hearts Club Band by The Beatles",
                log.getLog().trim());
    }

}
