package cloud.popples.marcopolo;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021年11月24日 15:17
 */
public class MarcoPoloTest {

    @Test
    public void testMarco () {
        while (true) {

        }
    }

    @Test
    public void testData() {
        Timestamp passTime = new Timestamp( new Date().getTime() );
        System.out.println(passTime);
        String timePart = new SimpleDateFormat("yyyyMMddHHmmss").format(passTime);
        System.out.println(timePart);
    }
}
