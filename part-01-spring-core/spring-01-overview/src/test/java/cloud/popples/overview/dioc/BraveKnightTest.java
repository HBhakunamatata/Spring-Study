package cloud.popples.overview.dioc;

import cloud.popples.overview.dioc.newway.BraveKnight;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class BraveKnightTest {

    @Test
    public void knightShouldEmbarkOnQuest() {
        Quest mockQuest = mock(Quest.class);
        BraveKnight braveKnight = new BraveKnight(mockQuest);
        braveKnight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();
    }
}
