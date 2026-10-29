package bear.section2;


import bear.section2.common.EmailNotificationClient;
import bear.section2.common.NotificationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("bear.section2")
public class ContextConfiguration {

    @Bean
    public NotificationClient notificationClient() {
        return new EmailNotificationClient(
                "apiUrl",
                "apikey",
                3
        );
    }
}
