package bear.section1.subsection3.constructor;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("bear.section1");
        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);

        System.out.println(bookService.selectAllBooks());
    }
}
