package CodeLinguists.codelingo.application;

import org.hsqldb.jdbcDriver;

import CodeLinguists.codelingo.exceptions.RuntimeExceptions.DBStateException;

public class Main {
    public static final String DB_FILE_NAME = "CodeLingoDB";
    private static String dbName = null;

    public static void setDBPathName(final String name) {
        //new jdbcDriver();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;

    }

    public static String getDbUrl() {
        if (dbName ==null || dbName.isBlank()) {
            throw new DBStateException(Strings.DbNotInitialized);
        }
        return Strings.HSQLDBUrl(dbName);
    }
}
