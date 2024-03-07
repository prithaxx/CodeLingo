package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.testng.annotations.BeforeSuite;

import codelinguists.codelingo.integration_tests.SessionManagerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SessionManagerIT.class
})

public class AllIntegrationTests {
    @BeforeSuite
    public void setupDb() {
        System.out.println("SADKJHASKDJGASJFKHGASFJHASGFJHASGDFJKHAG");
    }
}
