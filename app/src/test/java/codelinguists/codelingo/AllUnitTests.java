package codelinguists.codelingo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import codelinguists.codelingo.application.ServicesTest;
import codelinguists.codelingo.logic.ChapterHandlerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServicesTest.class,
    ChapterHandlerTest.class,
})
public class AllUnitTests {
}
