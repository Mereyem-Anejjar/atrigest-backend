package ma.zs.task.integration.core.utilisateur.utilisateur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class UtilisateurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("UtilisateurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
