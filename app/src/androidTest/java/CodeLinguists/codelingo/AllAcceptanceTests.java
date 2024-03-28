package CodeLinguists.codelingo;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import CodeLinguists.codelingo.persistence.utils.DbHelper;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginATest.class,
        NavigationATest.class,
        LessonATest.class,
        CourseProgressATest.class
})
public class AllAcceptanceTests {
    @BeforeClass
    public static void resetDb() {
        System.out.println("Reset DB!~");
        DbHelper.resetDB(getApplicationContext());
    }
}
