package cloud.popples.overview.aop;


import cloud.popples.overview.aop.eglog.UserServlet;
import cloud.popples.overview.aop.eglog.method_3.AnnotationPointCut;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectLogTest {

    @Test
    public void method01 () {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("proxy_context.xml");
        // 注意：实际返回的是动态生成的代理对象，需要使用接口承接，不能用实现类的句柄
        UserServlet impl01 = context.getBean("servletImpl01", UserServlet.class);
        impl01.add();
    }

    @Test
    public void method02 () {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("proxy_context.xml");
        UserServlet impl02 = context.getBean("servletImpl02", UserServlet.class);
        impl02.add();
    }

    @Test
    public void method03 () {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("proxy_context.xml");
        AnnotationPointCut annotationPointCut = context.getBean("annotationPointCut", AnnotationPointCut.class);
        UserServlet impl03 = context.getBean("servletImpl03", UserServlet.class);
        impl03.add();
    }
}
