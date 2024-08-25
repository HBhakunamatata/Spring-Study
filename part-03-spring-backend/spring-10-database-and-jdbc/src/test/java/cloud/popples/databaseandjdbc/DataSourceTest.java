package cloud.popples.databaseandjdbc;

import cloud.popples.databaseandjdbc.config.DataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@ContextConfiguration(classes = {DataSourceConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"default"})
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSourceNotNull() {
        Assert.assertNotNull(dataSource);
    }
}
