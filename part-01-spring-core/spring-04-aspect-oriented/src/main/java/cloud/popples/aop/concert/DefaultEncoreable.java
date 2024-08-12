package cloud.popples.aop.concert;

/**
 * @Description:
 * @author: hb
 * @date: 2023/05/13 17:20
 */


public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
        System.out.println("performEncore()");
    }
}
