package ma.zs.task.integration.core.commun.etat-avancement;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EtatAvancementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EtatAvancementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
