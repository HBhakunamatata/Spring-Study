package cloud.popples.overview.dioc;

import cloud.popples.overview.dioc.othereg.Servlet.EmpServlet;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpServiceTest {
    @Test
    public void HelloSpring() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        EmpServlet servlet = (EmpServlet) context.getBean("servlet");
        servlet.getEmp();
    }
}
