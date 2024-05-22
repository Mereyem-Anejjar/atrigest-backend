package ma.zs.task.integration.core.soc.role-associe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class RoleAssocieIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("RoleAssocieHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}
