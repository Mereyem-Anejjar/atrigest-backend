package ma.zs.task.integration.core.entite.entite-externe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EntiteExterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EntiteExterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
