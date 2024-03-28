package codelinguists.codelingo.unit_tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.unit_tests.logic.AccountHandlerTest;
import codelinguists.codelingo.unit_tests.logic.CourseHandlerTest;
import codelinguists.codelingo.unit_tests.logic.MockitoCourseHandlerTest;
import codelinguists.codelingo.unit_tests.logic.MokitoSessionManagerTest;
import codelinguists.codelingo.unit_tests.logic.QuizHandlerTest;
import codelinguists.codelingo.unit_tests.logic.QuizIteratorTest;
import codelinguists.codelingo.unit_tests.logic.SessionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    //logic
    AccountHandlerTest.class,
    CourseHandlerTest.class,
    MockitoCourseHandlerTest.class,
    MokitoSessionManagerTest.class,
    QuizHandlerTest.class,
    QuizIteratorTest.class,    
    SessionManagerTest.class,
})
public class AllUnitTests {}
