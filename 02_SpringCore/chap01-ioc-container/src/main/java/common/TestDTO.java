package common;
import org.springframework.stereotype.Component;



// section3 Annotation Config 기반 실습을 위해 추가한 어노테이션
// 해당 어노테이션이 붙어 있으면 Component Scan을 할때 자동으로
// 스프링 컨텍스트에 Bean으로 등록이 된다.
@Component
public class TestDTO {
    private int number;
}
