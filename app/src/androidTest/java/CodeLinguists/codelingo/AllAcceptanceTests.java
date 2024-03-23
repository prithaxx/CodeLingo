package CodeLinguists.codelingo;

import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import CodeLinguists.codelingo.persistence.utils.DbHelper;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginATest.class
})
public class AllAcceptanceTests {
    @BeforeClass
    public static void resetDb() {
        System.out.println("Reset DB!~");
        DbHelper.resetDB(getApplicationContext());
    }
}
