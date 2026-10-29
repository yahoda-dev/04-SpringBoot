package section2.javaconfig;

import common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // ApplicationContext = Bean을 관리하는 객체
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        MemberDTO member1 = (MemberDTO) context.getBean("member");
        System.out.println("member1 = " + member1);

        // class의 메타 정보를 이용해 가져오는 방법이라 타입 캐스팅이 필요 없음
        MemberDTO member2 = context.getBean(MemberDTO.class);
        System.out.println("member2 = " + member2);
    }
}
