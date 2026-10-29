package section01.aop;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)         // Annotation을 붙일 수 있는 위치
@Retention(RetentionPolicy.RUNTIME) // Annotation 정보를 언제까지 유지할지
//@Component                          // 컴포넌트를 추가해주면 AopLog 어노테이션을 붙인 함수가 Bean에 등록되게도 할 수 있음
public @interface AopLog {

}
