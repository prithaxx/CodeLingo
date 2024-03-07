package codelinguists.codelingo.integration_tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import CodeLinguists.codelingo.application.Main;

/**
 * This class should only be accessible within the integration test package
 * Thus it is made package-private
 */
class SqlTestRunner {
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(Main.getDbUrl(), "SA", "");
    }

    static void executeUpdate(String query) throws SQLException {
        try (Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }
}
