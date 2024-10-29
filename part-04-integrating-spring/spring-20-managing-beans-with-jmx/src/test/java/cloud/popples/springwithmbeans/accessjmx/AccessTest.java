package cloud.popples.springwithmbeans.accessjmx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.MBeanServerConnection;
import java.io.IOException;

@ContextConfiguration(classes = {JmxAccessConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccessTest {

    @Autowired
    private MBeanServerConnection mBeanServerConnection;

    @Test
    public void test() throws IOException {
        int mBeanCount = mBeanServerConnection.getMBeanCount();
        Assert.assertEquals(1, mBeanCount);
    }

}
