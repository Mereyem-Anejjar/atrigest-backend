package ma.zs.task.integration.core.notif.notification-detail;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class NotificationDetailIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("NotificationDetailHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
