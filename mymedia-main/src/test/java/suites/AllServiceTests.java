package suites;
;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import service.AuthServiceTest;
import service.RegistrationServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthServiceTest.class, RegistrationServiceTest.class})
public class AllServiceTests {

}
