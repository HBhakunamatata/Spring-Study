package cloud.popples.aop.concert;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: hb
 * @date: 2023/05/13 17:19
 */

@Aspect
@Component
public class EncoreableIntroducer {
    
    @DeclareParents(value = "cloud..aop.concert.Performance+",
                    defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
