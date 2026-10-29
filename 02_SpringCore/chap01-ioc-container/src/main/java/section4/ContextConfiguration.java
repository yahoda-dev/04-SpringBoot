package section4;


import common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 아래 어노테이션이 정의된 클래스가 설정 파일임을 의미한다.
@Configuration("configurationSection4")
public class ContextConfiguration {


    /*
    `@Bean`의 기본 Scope
    - Singleton
    */
    @Bean(name="member")
    public MemberDTO getMember() {
        return new MemberDTO(
                1,
                "user01",
                "pass01",
                "tester"
        );
    }
}
