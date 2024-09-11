package cloud.popples.advanedwiring;

import cloud.popples.advancedwiring.desserts.Dessert;
import cloud.popples.advancedwiring.desserts.DessertClient;
import cloud.popples.advancedwiring.desserts.DessertMarkInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DessertMarkInterface.class)
public class DessertTest {

    @Autowired
    private Dessert primaryDessert;

    @Autowired
    private DessertClient dessertClient;

    @Test
    public void testPrimaryDessertNotNull() {
        Assert.assertNotNull(primaryDessert);
        String className  = primaryDessert.getClass().getName();
        System.out.println(className);
    }


    @Test
    public void testQualifierDessert() {
        Assert.assertNotNull(dessertClient);
        Assert.assertNotNull(dessertClient.getCookies());
        Assert.assertNotNull(dessertClient.getIceCream());
    }
}
