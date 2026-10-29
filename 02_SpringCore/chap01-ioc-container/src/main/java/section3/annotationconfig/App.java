package section3.annotationconfig;

import common.MemberDTO;
import common.TestDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // ApplicationContext = Bean을 관리하는 객체
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        // Spring Container에 생성된 bean 객체들의 이름을 배열로 반환
        String[] beanNames = context.getBeanDefinitionNames();

        for(String name : beanNames) {
            System.out.println("name = " + name);
            // 필터링 전 result: contextConfiguration, memberDAO, testDTO
        }

    }
}
