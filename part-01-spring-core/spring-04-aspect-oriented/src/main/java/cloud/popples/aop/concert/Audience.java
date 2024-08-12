package cloud.popples.aop.concert;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Audience {

    @Pointcut("execution(* cloud.*.concert.Performance.perform())")
    public void performance() {}

    @Before("performance()")
    public void silencePhones(JoinPoint joinPoint) {
        System.out.println("Silencing cell phones");
    }

    @Before("performance()")
    public void takeSeat(JoinPoint joinPoint) {
        System.out.println("Taking seat");
    }

    @AfterReturning("performance()")
    public void applause(JoinPoint joinPoint) {
        System.out.println("Clap Clap Clap");
    }

    @AfterThrowing("performance()")
    public void demandRefund(JoinPoint joinPoint) {
        System.out.println("Demanding a refund");
    }

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("Around before performance");
            joinPoint.proceed();
            System.out.println("Around after performance");
        } catch (Throwable throwable) {
            System.out.println("Around exception");
        }
    }

}
