package codelinguists.codelingo.integration_tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    static ResultSet executeQuery(String query) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(query)) {
             return ps.executeQuery();
        }
    }

    public static boolean checkValue(String tableName, String whereClause) throws SQLException {
        String query = "SELECT * FROM "+tableName+" WHERE "+whereClause;
        try (Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    }

    //Taken from https://stackoverflow.com/questions/24229442/print-the-data-in-resultset-along-with-column-names
    //Used for debugging
    private static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }
}
