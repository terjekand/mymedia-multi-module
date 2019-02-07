package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import security.SessionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({SessionManagerTest.class})
public class AllSecurityTests {

}
