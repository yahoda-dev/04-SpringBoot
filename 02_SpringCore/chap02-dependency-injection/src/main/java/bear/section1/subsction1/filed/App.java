package bear.section1.subsction1.filed;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("bear.section1");
        BookService bookService = context.getBean("bookServiceField", BookService.class);

        System.out.println(bookService.searchBookBySequence(1));
    }
}
