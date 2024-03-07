package codelinguists.codelingo.unit_tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import codelinguists.codelingo.unit_tests.application.ServicesTest;
import codelinguists.codelingo.unit_tests.dso.AccountObjTest;
import codelinguists.codelingo.unit_tests.dso.ChapterObjTest;
import codelinguists.codelingo.unit_tests.dso.CourseObjTest;
import codelinguists.codelingo.unit_tests.dso.QuizObjTest;
import codelinguists.codelingo.unit_tests.logic.AccountHandlerTest;
import codelinguists.codelingo.unit_tests.logic.ExceptionsTest;
import codelinguists.codelingo.unit_tests.logic.QuizHandlerTest;
import codelinguists.codelingo.unit_tests.logic.SessionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServicesTest.class,

    //logic
    AccountHandlerTest.class,
    ExceptionsTest.class,
    QuizHandlerTest.class,
    SessionManagerTest.class,

    //DSOs
    AccountObjTest.class,
    ChapterObjTest.class,
    CourseObjTest.class,
    QuizObjTest.class
})
public class AllUnitTests {}
