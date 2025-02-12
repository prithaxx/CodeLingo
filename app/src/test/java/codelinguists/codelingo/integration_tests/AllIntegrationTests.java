package codelinguists.codelingo.integration_tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.testng.annotations.BeforeSuite;

import codelinguists.codelingo.integration_tests.SessionManagerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DBAccessTest.class,
        SessionManagerIT.class,
        SessionManagerLogoutIT.class,
        SessionManagerQuizIteratorIT.class
})

public class AllIntegrationTests {
}
