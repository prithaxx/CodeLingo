package CodeLinguists.codelingo.persistence.hsqldb;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.utils.DBHelper;

public class AccountDataHSQLDB implements IAccountData {
    private final String dbPath;

    public AccountDataHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    @NotNull
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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PUBLIC.ACCOUNT VALUES (DEFAULT, ?, TRUE, 0, ?, '')");
            ps.setString(1, name);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();

            ps = connection.prepareStatement("select * from ACCOUNT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                boolean isGuest = rs.getBoolean("isGuest");
                int activeCourse = rs.getInt("ActiveCourseId");
                String username = rs.getString("username");
                String password = rs.getString("password");
            }

            ps = connection.prepareStatement("select * from ACCOUNT where USERNAME = ? and NAME = ?");
            ps.setString(1, name);
            ps.setString(2, name);
            rs = ps.executeQuery();



            if(rs.next()) {
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                boolean isGuest = rs.getBoolean("isGuest");
                int activeCourse = rs.getInt("ActiveCourseId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new AccountObj(id, name2, isGuest, activeCourse, username, password);
            }

//            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    int id = generatedKeys.getInt(1);
//                    return new AccountObj(id, name, true, 0, "", "");
//                } else {
//                    throw new SQLException("Creating account failed, no ID obtained.");
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
