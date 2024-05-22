package ma.zs.task.integration.core.notif.notification;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class NotificationIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("NotificationHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
