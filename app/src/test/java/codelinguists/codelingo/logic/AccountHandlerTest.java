package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.logic.AccountHandler;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;

public class AccountHandlerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = InputValidationException.class)
    public void guestLoginNullInput() {
        AccountDataMock accountData = new AccountDataMock(false, false, false);
        AccountHandler accountHandler = new AccountHandler(accountData, null);
        accountHandler.guestLogin(null);
    }

    @Test(expected = InputValidationException.class)
    public void guestLoginEmptyInput() {
        AccountDataMock accountData = new AccountDataMock(false, false, false);
        AccountHandler accountHandler = new AccountHandler(accountData, null);
        accountHandler.guestLogin("");
    }

    @Test
    public void guestLoginNoAccount() {
        AccountDataMock accountData = new AccountDataMock(false, false, true);
        AccountHandler accountHandler = new AccountHandler(accountData, new SessionDataMock());
        AccountObj acc = accountHandler.guestLogin("test");
        assertEquals(acc.getName(), "test");
    }

    @Test
    public void guestLoginExistingAccount() {
        AccountDataMock accountData = new AccountDataMock(false, false, false);
        AccountHandler accountHandler = new AccountHandler(accountData, new SessionDataMock());
        AccountObj acc = accountHandler.guestLogin("test");
        assertEquals(acc.getName(), "test");
    }

    @Test
    public void guestLoginDefaultConstructor() {
        AccountHandler accountHandler = new AccountHandler();
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
            return new AccountObj(0,name,true,null,null,null);
        }

        @Override
        public AccountObj createGuestAccount(String name) {
            if (isCreateGuestNull){
                return null;
            }
            AccountObj localAccount = new AccountObj(0,name,true,null,null,null);
            if (enableCreation) {
                account=localAccount;
            }
            return localAccount;
        }
    }
    class SessionDataMock implements ISessionData {

        private AccountObj obj;
        @Override
        public void setActiveAccount(AccountObj account) {
            obj=account;
        }

        public boolean isActiveAccountSet() {
            return obj!=null;
        }
    }
}