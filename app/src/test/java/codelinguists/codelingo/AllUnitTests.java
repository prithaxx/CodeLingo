package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.application.ServicesTest;
import codelinguists.codelingo.logic.AccountHandlerTest;
import codelinguists.codelingo.logic.ExceptionsTest;
import codelinguists.codelingo.logic.QuizHandlerTest;
import codelinguists.codelingo.logic.SessionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServicesTest.class,
    AccountHandlerTest.class,
    ExceptionsTest.class,
    QuizHandlerTest.class,
    SessionManagerTest.class,
})
public class AllUnitTests {
}
