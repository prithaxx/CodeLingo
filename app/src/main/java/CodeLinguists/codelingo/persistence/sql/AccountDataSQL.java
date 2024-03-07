package CodeLinguists.codelingo.persistence.sql;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.persistence.persistence_exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
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
        try (ResultSet rs = sqlRunner.selectAccountByName(name)){
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
        try (ResultSet rs = sqlRunner.selectAccountByName(name)){
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
        try {
            sqlRunner.updateAccountActiveCourse(accountId, courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
