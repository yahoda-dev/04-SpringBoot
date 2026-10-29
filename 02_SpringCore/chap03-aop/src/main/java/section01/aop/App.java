package section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        MemberService memberService = context.getBean(MemberService.class);
        System.out.println("========== Target 실행 ==========");
        System.out.println(memberService.processMember(false));

        // within 호출 확인용
        memberService.selectMembers();

        // this 예제
        memberService.checkMember();

        // args 예제
        memberService.registerMember("Jiwon", 28);

        // annotation 예제
        memberService.updateMember();
    }
}
