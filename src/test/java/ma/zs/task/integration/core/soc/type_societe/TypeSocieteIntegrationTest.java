package ma.zs.task.integration.core.soc.type-societe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TypeSocieteIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TypeSocieteHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
