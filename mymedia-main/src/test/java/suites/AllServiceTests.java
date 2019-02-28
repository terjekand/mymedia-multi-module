package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import service.AuthInterfTest;
import service.RegistrationServiceTest;

;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthInterfTest.class, RegistrationServiceTest.class})
public class AllServiceTests {

}
