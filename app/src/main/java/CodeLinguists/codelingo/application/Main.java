package CodeLinguists.codelingo.application;

import org.hsqldb.jdbcDriver;

import CodeLinguists.codelingo.exceptions.RuntimeExceptions.DBStateException;

public class Main {
    public static final String DB_FILE_NAME = "CodeLingoDB";
    private static String dbName = null;

    public static void setDBPathName(final String name) {
        new jdbcDriver();
        dbName = name;
    }

    public static String getDbUrl() {
        if (dbName ==null || dbName.isBlank()) {
            throw new DBStateException(Strings.DbNotInitialized);
        }
        return Strings.HSQLDBUrl(dbName);
    }
}
