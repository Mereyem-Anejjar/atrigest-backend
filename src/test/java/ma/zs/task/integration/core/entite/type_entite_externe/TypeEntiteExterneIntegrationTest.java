package ma.zs.task.integration.core.entite.type-entite-externe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeEntiteExterneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeEntiteExterneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
