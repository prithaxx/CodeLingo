package codelinguists.codelingo.unit_tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.unit_tests.application.ServicesTest;
import codelinguists.codelingo.unit_tests.dso.AccountObjTest;
import codelinguists.codelingo.unit_tests.dso.ChapterObjTest;
import codelinguists.codelingo.unit_tests.dso.CourseObjTest;
import codelinguists.codelingo.unit_tests.dso.QuizObjTest;
import codelinguists.codelingo.unit_tests.logic.AccountHandlerTest;
import codelinguists.codelingo.unit_tests.logic.QuizHandlerTest;
import codelinguists.codelingo.unit_tests.logic.SessionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServicesTest.class,

    //logic
    AccountHandlerTest.class,
    QuizHandlerTest.class,
    SessionManagerTest.class,

    //DSOs
    AccountObjTest.class,
    ChapterObjTest.class,
    CourseObjTest.class,
    QuizObjTest.class
})
public class AllUnitTests {}
