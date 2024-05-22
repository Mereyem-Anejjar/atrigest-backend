package ma.zs.task.integration.core.commun.banque;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class BanqueIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("BanqueHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
