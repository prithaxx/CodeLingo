package CodeLinguists.codelingo.persistence.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HSQLDBRunner implements ISqlRunner {
    private final String dbUrl;

    public HSQLDBRunner(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(dbUrl, "SA", "");
    }
}
