package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.application.ServicesTest;
import codelinguists.codelingo.logic.AccountHandlerTest;
import codelinguists.codelingo.logic.ChapterHandlerTest;
import codelinguists.codelingo.logic.CourseHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServicesTest.class,
    AccountHandlerTest.class
        //ChapterHandlerTest.class,
        //CourseHandlerTest.class
})
public class AllUnitTests {
}
