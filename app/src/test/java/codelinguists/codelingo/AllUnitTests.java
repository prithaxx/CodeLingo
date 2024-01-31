package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import CodeLinguists.codelingo.application.Services;
import codelinguists.codelingo.logic.AccountHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    Services.class,
    AccountHandlerTest.class
})
public class AllUnitTests {
}
