package cloud.popples.springstudy.advanedwiring;


import advancedwiring.scope.Notepad;
import advancedwiring.scope.ScopeClient1;
import advancedwiring.scope.ScopeClient2;
import advancedwiring.scope.ScopeMarkedInterface;
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
