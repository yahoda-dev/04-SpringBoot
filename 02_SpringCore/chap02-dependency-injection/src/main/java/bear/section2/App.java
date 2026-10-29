package bear.section2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beans = context.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println("bean = " + bean);
        }
    }
}
