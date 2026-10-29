package section01.aop;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MemberAspect {
    @Pointcut("execution(* section01.aop.MemberServiceImpl.processMember(..))")
    public void advicePointCut() {

    }

    @Before("advicePointCut()")
    public void beforeAdvice() {
        System.out.println("[Before] 메서드 실행 전...");
    }

    @After("advicePointCut()")
    public void afterAdvice() {
        System.out.println("[After] 메서드 실행 후...");
    }

    @AfterReturning(value = "advicePointCut()", returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("[afterReturningAdvice] 반환값 : " + result);
    }

    @Before("within(section01.aop.MemberServiceImpl) && execution(* selectMembers(..))")
    public void withinPointCut() {
        System.out.println("[within] MemberServiceImpl 적용");
    }

    @Before("this(section01.aop.MemberService) && execution(* checkMember(..))")
    public void thisPointCut() {
        System.out.println("[this] MemberService 적용");
    }

    @Before(value = "args(name, age) && execution(* registerMember(..))", argNames = "name, age")
    public void argsPointCut(String name, int age) {
        System.out.println("[args] Name, Age 적용");
    }

    @Before("@annotation(AopLog)")
    public void annotationPointCut() {
        System.out.println("[annotationPointCut] @AopLog 적용");
    }
}
