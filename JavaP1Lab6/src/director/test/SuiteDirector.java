package director.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value= Suite.class)
@Suite.SuiteClasses(value = {TestDirector.class,
                            TestFactory.class})
public class SuiteDirector {
}
