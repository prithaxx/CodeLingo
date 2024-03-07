package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.dso.LocalPreferences;
import CodeLinguists.codelingo.persistence.persistence_exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;

public class AccountHandler implements IAccountHandler {

    private final IAccountData accountData;
    private final ISessionData sessionData;

    public AccountHandler(IAccountData accountData, ISessionData sessionData) {
        this.accountData = accountData;
        this.sessionData = sessionData;
    }

    @Override
    public AccountObj guestLogin(String name) throws DataInaccessibleException {
        return guestLogin(name, false);
    }

    @Override
    public AccountObj guestLogin(String name, boolean stayLoggedIn) throws DataInaccessibleException {
        if(name == null || name.isEmpty()){
            throw new InputValidationException(Strings.NoName);
        }

        AccountObj account = null;
        try {
            account = accountData.getGuestAccountByName(name);
        } catch (AccountNotFoundException e) {
            account = accountData.createGuestAccount(name);
        }

        accountData.setStayLoggedIn(account.getId(), stayLoggedIn);
        updateSessionData(account);
        return account;
    }

    @Override
    public void logout() {
        accountData.setStayLoggedIn(-1, false);
    }

    @Override
     // Returns Null if autologin fails
    public AccountObj autoLogin() {
        try {
            LocalPreferences lp = accountData.getLocalPreferences();
            if (lp.autoLogin() && lp.accountId()>0) {
                return accountData.getGuestAccountById(lp.accountId());
            }
        } catch (DataInaccessibleException | AccountNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setActiveCourse(AccountObj account, int courseId) {
        accountData.setActiveCourse(account.getId(), courseId);
        account.setActiveCourseId(courseId);
    }

    private void updateSessionData(AccountObj account) {
        if (account != null) {
            sessionData.setActiveAccount(account);
        }
    }
}
