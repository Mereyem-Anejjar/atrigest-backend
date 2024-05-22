package ma.zs.task.integration.core.task.tache-entite-externe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TacheEntiteExterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TacheEntiteExterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
