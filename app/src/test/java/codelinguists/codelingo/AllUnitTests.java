package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.application.ServicesTest;
import codelinguists.codelingo.dso.AccountObjTest;
import codelinguists.codelingo.dso.ChapterObjTest;
import codelinguists.codelingo.dso.CourseObjTest;
import codelinguists.codelingo.dso.QuizObjTest;
import codelinguists.codelingo.logic.AccountHandlerTest;
import codelinguists.codelingo.logic.CourseHandlerTest;
import codelinguists.codelingo.logic.QuizHandlerTest;
import codelinguists.codelingo.logic.QuizIteratorTest;
import codelinguists.codelingo.logic.SessionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServicesTest.class,

    //logic
    AccountHandlerTest.class,
        CourseHandlerTest.class,
    QuizHandlerTest.class,
        QuizIteratorTest.class,
    SessionManagerTest.class,

    //DSOs
    AccountObjTest.class,
    ChapterObjTest.class,
    CourseObjTest.class,
    QuizObjTest.class
})
public class AllUnitTests {
}
