package codelinguists.codelingo.integration_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import codelinguists.codelingo.utils.TestUtils;

public class DBAccessTest {
    private File tempDB;

    @Test
    public void testDbInitDataAccess() throws IOException, SQLException {
        this.tempDB = TestUtils.copyDB();
        try (ResultSet rs = SqlTestRunner.executeQuery("SELECT * FROM LOCAL_PREFERENCES")){
            assertTrue(rs.next());
        }
    }

    @Test
    public void testDbUpdateAccess() throws IOException, SQLException {
        this.tempDB = TestUtils.copyDB();

        SqlTestRunner.executeUpdate("INSERT INTO COURSE VALUES(555, 'name', 'description')");
        try (ResultSet rs = SqlTestRunner.executeQuery("SELECT * FROM COURSE WHERE id = 555")){
            assertTrue(rs.next());
        }
    }

    @Test
    public void testDbReset() throws IOException, SQLException {
        this.tempDB = TestUtils.copyDB();

        SqlTestRunner.executeUpdate("INSERT INTO COURSE VALUES(555, 'name', 'description')");

        this.tempDB = TestUtils.copyDB();

        try (ResultSet rs = SqlTestRunner.executeQuery("SELECT * FROM COURSE WHERE id = 555")){
            assertFalse(rs.next());
        }
    }
}
