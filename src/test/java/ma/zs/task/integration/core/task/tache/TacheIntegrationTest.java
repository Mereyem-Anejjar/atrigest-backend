package ma.zs.task.integration.core.task.tache;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TacheIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TacheHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
