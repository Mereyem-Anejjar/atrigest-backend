package ma.zs.task.integration.core.commun.priorite;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class PrioriteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("PrioriteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
