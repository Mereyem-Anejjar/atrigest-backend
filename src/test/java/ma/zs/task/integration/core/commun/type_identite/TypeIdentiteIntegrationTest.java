package ma.zs.task.integration.core.commun.type-identite;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeIdentiteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeIdentiteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
