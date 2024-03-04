package CodeLinguists.codelingo.persistence.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISqlRunner {
    public Connection connect() throws SQLException;
    //TODO access stored sql queries
}
