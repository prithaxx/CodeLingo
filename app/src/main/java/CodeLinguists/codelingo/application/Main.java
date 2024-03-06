package CodeLinguists.codelingo.application;

import org.hsqldb.jdbcDriver;

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
        if (dbName == null || dbName.isBlank()) {
            //TODO!!! fix this error to something meaningful
            throw new RuntimeException("Database is not instantiate yet!");
        }
        return "jdbc:hsqldb:file:"+dbName+";shutdown=true";
    }
}
