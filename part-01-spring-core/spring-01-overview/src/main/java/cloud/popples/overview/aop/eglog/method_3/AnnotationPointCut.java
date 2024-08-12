package cloud.popples.overview.aop.eglog.method_3;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationPointCut {

    @Before("execution(* cloud.*.servlet.method_3.ServletImpl03.*(..))")
    public void logBefore03 () {
        System.out.println("Log Before ...");
    }

    @After("execution(* cloud.*.servlet.method_3.ServletImpl03.*(..))")
    public void logAfter03 () {
        System.out.println("Log after ...");
    }
}
