package cloud.popples.advanedwiring;

import cloud.popples.advancedwiring.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@ActiveProfiles("dev")
//@ActiveProfiles("prod")
public class PersistenceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void context() {
        System.out.println(dataSource);
    }
}
