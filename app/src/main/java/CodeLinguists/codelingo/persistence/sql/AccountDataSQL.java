package CodeLinguists.codelingo.persistence.sql;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.LocalPreferences;
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
        try {
            return rsToAccountObj(sqlRunner.selectAccountByName(name));
        } catch (SQLException e) {
            throw new AccountNotFoundException(Strings.AccountNotFoundWithName(name), e);
        }
    }

    @Override
    public AccountObj getGuestAccountById(int accountId) throws AccountNotFoundException {
        try {
            return rsToAccountObj(sqlRunner.selectAccountById(accountId));
        } catch (SQLException e) {
            throw new AccountNotFoundException(Strings.AccountNotFound, e);
        }
    }

    @Override
    public AccountObj createGuestAccount(String name) throws DataInaccessibleException {
        try {
            return rsToAccountObj(sqlRunner.insertGuestAccount(name));
        } catch (SQLException | AccountNotFoundException e) {
            throw new DataInaccessibleException(Strings.CannotCreateAccount, e);
        }
    }

    @Override
    public void setActiveCourse(int accountId, int courseId) {
        try {
            sqlRunner.updateAccountActiveCourse(accountId, courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStayLoggedIn(int accountId, boolean stayLoggedIn) {
        try {
            sqlRunner.updateLocalPreferencesAutoLogin(stayLoggedIn, accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LocalPreferences getLocalPreferences() throws DataInaccessibleException {
        try (ResultSet rs = sqlRunner.selectLocalPreferences()){
            if (rs.next()) {
                boolean autoLogin = rs.getBoolean("autoLogin");
                int activeAccountId = rs.getInt("activeAccountId");
                return new LocalPreferences(autoLogin, activeAccountId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DataInaccessibleException(Strings.CannotFindPreferences);
    }

    @Override
    public void initLocalPreferences() {
        try (ResultSet rs = sqlRunner.selectLocalPreferences()) {
            if (!rs.next()){
                sqlRunner.insertLocalPreferences();
            } else {
                setStayLoggedIn(1, false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private AccountObj rsToAccountObj(ResultSet rs) throws AccountNotFoundException {
        try (rs) {
            if (rs.next()) {
                int id = rs.getInt("id");
                boolean isGuest = rs.getBoolean("isGuest");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int activeCourseId = rs.getInt("activeCourseId");

                return new AccountObj(id, name, isGuest, activeCourseId, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new AccountNotFoundException(Strings.AccountNotFound);
    }
}
