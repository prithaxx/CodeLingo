package CodeLinguists.codelingo.application;

import org.hsqldb.jdbcDriver;

import java.lang.reflect.InvocationTargetException;

public class Main {
    private static String dbName = "CodeLingoDB";

    public static void setDBPathName(final String name) {
        new jdbcDriver();
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}
