package cloud.popples.advanedwiring;


import cloud.popples.advancedwiring.scope.Notepad;
import cloud.popples.advancedwiring.scope.ScopeClient1;
import cloud.popples.advancedwiring.scope.ScopeClient2;
import cloud.popples.advancedwiring.scope.ScopeMarkedInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScopeMarkedInterface.class)
public class ScopeTest {

    @Autowired
    private ScopeClient1 scopeClient1;

    @Autowired
    private ScopeClient2 scopeClient2;


    @Test
    public void testPrototypeBean_thenNotEquals() {
        Notepad notepad1 = scopeClient1.getNotepad();
        Notepad notepad2 = scopeClient2.getNotepad();
        Assert.assertNotSame(notepad1, notepad2);
    }
}
