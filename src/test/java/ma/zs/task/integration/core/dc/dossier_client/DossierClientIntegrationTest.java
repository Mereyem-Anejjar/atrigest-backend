package ma.zs.task.integration.core.dc.dossier-client;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DossierClientIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DossierClientHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
