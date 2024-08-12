package cloud.popples.wiringbean.soundsystem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc sgtPeppers;

    @Autowired
    private MediaPlayer cdPlayer;

    @Test
    public void testNotNull() {
        assertNotNull(sgtPeppers);
    }

    @Test
    public void testPlay() {
        cdPlayer.play();
        assertEquals(
                "Playing Sgt.Pepper\'s Lonely Hearts Club Band by The Beatles",
                log.getLog().trim()
        );
    }

}
