package ma.zs.task.integration.core.soc.societe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class SocieteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("SocieteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
