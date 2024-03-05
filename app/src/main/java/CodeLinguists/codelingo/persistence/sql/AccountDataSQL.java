package CodeLinguists.codelingo.persistence.sql;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.utils.ISqlRunner;

public class AccountDataSQL implements IAccountData {
    private final ISqlRunner sqlRunner;

    public AccountDataSQL(ISqlRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @Override
    @NotNull
    public AccountObj getGuestAccountByName(String name) throws AccountNotFoundException {
        AccountObj account = null;
        try (Connection connection = sqlRunner.connect()){
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

        if (account == null) {
            throw new AccountNotFoundException(Strings.AccountNotFound(name));
        } else {
            return account;
        }
    }

    @Override
    public AccountObj createGuestAccount(String name) throws DataInaccessibleException {
        try (Connection connection = sqlRunner.connect()){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PUBLIC.ACCOUNT VALUES (DEFAULT, ?, TRUE, 1, ?, '')");
            ps.setString(1, name);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();

            ps = connection.prepareStatement("select * from ACCOUNT where USERNAME = ? and NAME = ?");
            ps.setString(1, name);
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                boolean isGuest = rs.getBoolean("isGuest");
                int activeCourse = rs.getInt("ActiveCourseId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new AccountObj(id, name2, isGuest, activeCourse, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DataInaccessibleException(Strings.CannotCreateAccount);
    }

    @Override
    public void setActiveCourse(int accountId, int courseId) {
        try (Connection connection = sqlRunner.connect()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE ACCOUNT set ActiveCourseId = ? where id = ?");
            ps.setInt(1, courseId);
            ps.setInt(2, accountId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
