package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import CodeLinguists.codelingo.application.Services;
import codelinguists.codelingo.logic.AccountHandlerTest;
import codelinguists.codelingo.logic.ChapterHandlerTest;
import codelinguists.codelingo.logic.CourseHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    Services.class,
    AccountHandlerTest.class
        //ChapterHandlerTest.class,
        //CourseHandlerTest.class
})
public class AllUnitTests {
}
