package section3.annotationconfig;


import common.MemberDAO;
import common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@ComponentScan(basePackages = "common",
        useDefaultFilters = false, // 필터링 적용을 위해선 기존 필터를 꺼야 한다.
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {MemberDAO.class} // class type으로 제한을 한다.
                )
        }
)
// 하위 컴포넌트를 다 스캔해서 Bean으로 등록해줌
// @Component 계열의 어노테이션을 스캔해 모두 Bean으로 등록해준다.
// EX) @Controller, @Service, @Repository, @Entity ...
// basePackages(어디서 찾을지)를 지정해 주어야 한다.
// 컴포넌트 스캔은 필터도 가능하다.
// - includeFilters : 그 범위 안에서 어떤 것을 포함할지
// - excludeFilters : 그 범위 안에서 어떤 것을 제외할지
@Configuration // 해당 어노테이션이 정의된 클래스가 설정 파일임을 의미한다.
public class ContextConfiguration {
}
