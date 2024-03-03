package CodeLinguists.codelingo.application;

import org.hsqldb.jdbcDriver;

public class Main {
    public static final String DB_FILE_NAME = "CodeLingoDB";
    private static String dbName = null;

    public static void setDBPathName(final String name) {
        new jdbcDriver();
        dbName = name;
    }

    public static String getDbUrl() {
        if (dbName ==null || dbName.isBlank()) {
            //TODO!!! fix this error to something meaningful
            throw new RuntimeException("This is maybe bad?");
        }
        return "jdbc:hsqldb:file:"+dbName+";shutdown=true";
    }
}
