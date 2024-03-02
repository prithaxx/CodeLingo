package CodeLinguists.codelingo.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ICourseData;

public class AccountDataHSQLDB implements IAccountData {
    private final String dbPath;

    public AccountDataHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public AccountObj getGuestAccountByName(String name){
        AccountObj account = null;
        try (Connection connection = connect()){
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                int id = rs.getInt("id");
                boolean isGuest = rs.getBoolean("isGuest");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int activeCourseId = rs.getInt("activeCourseId");

                account = new AccountObj(id, name, isGuest, activeCourseId, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public AccountObj createGuestAccount(String name){
        try (Connection connection = connect()){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ACCOUNT (name, isGuest, activeCourseId, username, password) VALUES (?, TRUE, 0, '', '')");
            ps.setString(1, name);
            ps.executeQuery();
            ps.close();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new AccountObj(id, name, true, 0, "", "");
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
