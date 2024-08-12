package cloud.popples.aop.soundsystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {
    @Autowired
    private CompactDisc compactDisc;
    @Autowired
    private TrackCounter trackCounter;

    @Test
    public void testTrackCounter() {
        compactDisc.playTrack(1);
        compactDisc.playTrack(2);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(3);
        compactDisc.playTrack(4);
        compactDisc.playTrack(4);

        Assert.assertEquals(1, trackCounter.getPlayCount(1));
        Assert.assertEquals(1, trackCounter.getPlayCount(2));
        Assert.assertEquals(4, trackCounter.getPlayCount(3));
        Assert.assertEquals(2, trackCounter.getPlayCount(4));
        Assert.assertEquals(0, trackCounter.getPlayCount(5));

    }
}
