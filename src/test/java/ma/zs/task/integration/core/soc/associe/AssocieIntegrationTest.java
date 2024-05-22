package ma.zs.task.integration.core.soc.associe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AssocieIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AssocieHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
