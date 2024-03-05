package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;

public class AccountHandlerTest {

    @Test(expected = InputValidationException.class)
    public void guestLoginNullInput() throws DataInaccessibleException {
        AccountDataMock accountData = new AccountDataMock(false, false, false);
        AccountHandler accountHandler = new AccountHandler(accountData, null);
        accountHandler.guestLogin(null);
    }

    @Test(expected = InputValidationException.class)
    public void guestLoginEmptyInput() throws DataInaccessibleException {
        AccountDataMock accountData = new AccountDataMock(false, false, false);
        AccountHandler accountHandler = new AccountHandler(accountData, null);
        accountHandler.guestLogin("");
    }

    @Test
    public void guestLoginNoAccount() throws DataInaccessibleException {
        AccountDataMock accountData = new AccountDataMock(false, false, true);
        AccountHandler accountHandler = new AccountHandler(accountData, new SessionDataMock());
        AccountObj acc = accountHandler.guestLogin("test");
        assertEquals(acc.getName(), "test");
    }

    @Test
    public void guestLoginExistingAccount() throws DataInaccessibleException {
        AccountDataMock accountData = new AccountDataMock(false, false, false);
        AccountHandler accountHandler = new AccountHandler(accountData, new SessionDataMock());
        AccountObj acc = accountHandler.guestLogin("test");
        assertEquals(acc.getName(), "test");
    }

    @Test
    public void guestLoginDefaultConstructor() throws DataInaccessibleException {
        AccountHandler accountHandler = new AccountHandler(Services.getAccountData(), Services.getSessionData());
        AccountObj acc = accountHandler.guestLogin("test");
        assertEquals(acc.getName(), "test");
    }

    class AccountDataMock implements IAccountData{
        boolean isGetGuestNull;
        boolean isCreateGuestNull;
        boolean enableCreation;

        private AccountObj account;

        public AccountDataMock(boolean isGetGuestNull, boolean isCreateGuestNull, boolean enableCreation) {
            this.isGetGuestNull = isGetGuestNull;
            this.isCreateGuestNull = isCreateGuestNull;
            this.enableCreation = enableCreation;
        }
        @Override
        public AccountObj getGuestAccountByName(String name) {
            if (enableCreation) {
                return account;
            }
            if (isGetGuestNull){
                return null;
            }
            return new AccountObj(0,name,true,-1,null,null);
        }

        @Override
        public AccountObj createGuestAccount(String name) {
            if (isCreateGuestNull){
                return null;
            }
            AccountObj localAccount = new AccountObj(0,name,true,-1,null,null);
            if (enableCreation) {
                account=localAccount;
            }
            return localAccount;
        }

        @Override
        public void setActiveCourse(int accountId, int courseId) {

        }
    }
    class SessionDataMock implements ISessionData {

        private AccountObj obj;
        @Override
        public void setActiveAccount(AccountObj account) {
            obj=account;
        }

        @Override
        public CourseObj getActiveCourse() {
            return null;
        }

        public boolean isActiveAccountSet() {
            return obj!=null;
        }
    }
}