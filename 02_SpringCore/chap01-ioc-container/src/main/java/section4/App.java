package section4;

import common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // 1. 일반적인 객체 생성 방법 (mem1과 mem2의 주소값은 다르다)
        MemberDTO mem1 = new MemberDTO(1, "user1", "pwd1", "USER01");
        MemberDTO mem2 = new MemberDTO(1, "user1", "pwd1", "USER01");

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // 2. Java Config로 객체 생성 (mem3과 mem4의 주소값은 같다. = 같은 객체를 반환한다.)
        MemberDTO mem3 = context.getBean("member", MemberDTO.class);
        MemberDTO mem4 = context.getBean("member", MemberDTO.class);
        System.out.println("2 : " + (mem3 == mem4));

    }
}
