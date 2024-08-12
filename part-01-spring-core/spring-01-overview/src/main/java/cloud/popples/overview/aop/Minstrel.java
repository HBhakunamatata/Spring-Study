package cloud.popples.overview.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.PrintStream;

@Aspect
public class Minstrel {

    private PrintStream stream;

    public Minstrel(PrintStream printStream) {
        this.stream = printStream;
    }

    @Pointcut("execution(* cloud.popples.overview.*.embarkOnQuest(..))")
    public void executeEmbark() {
    }

    @Before("executeEmbark()")
    public void singBeforeQuest() {
        stream.println("Before ...");
    }

    @After("executeEmbark()")
    public void singAfterQuest() {
        stream.println("After ...");
    }
}
