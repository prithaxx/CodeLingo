package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.integration_tests.AllIntegrationTests;
import codelinguists.codelingo.unit_tests.AllUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllUnitTests.class,
        AllIntegrationTests.class

})
public class AllTests {
}
