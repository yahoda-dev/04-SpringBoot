package bear.section2.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationService {
    private NotificationClient client;


    public NotificationService(NotificationClient client) {
        this.client = client;
    }
}
