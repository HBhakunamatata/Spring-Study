package cloud.popples.overview.aop.eglog.method_1;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class LogBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println( target.getClass().getName() +
                "的" + method.getName() + "方法被执行了"
                + "函数的参数为" + args);
    }
}