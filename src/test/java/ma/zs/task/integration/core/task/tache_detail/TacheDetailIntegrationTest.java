package ma.zs.task.integration.core.task.tache-detail;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TacheDetailIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TacheDetailHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
