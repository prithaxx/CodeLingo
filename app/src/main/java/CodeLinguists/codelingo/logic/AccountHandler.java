package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.preferencesObj;
import CodeLinguists.codelingo.persistence.persistence_exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.IAccountData;

public class AccountHandler implements IAccountHandler {

    private final IAccountData accountData;

    public AccountHandler(IAccountData accountData) {
        this.accountData = accountData;
    }

    @Override
    public AccountObj guestLogin(String name) throws DataInaccessibleException, InputValidationException {
        return guestLogin(name, false);
    }

    @Override
    public AccountObj guestLogin(String name, boolean stayLoggedIn) throws DataInaccessibleException, InputValidationException {
        if(name == null || name.isEmpty()){
            throw new InputValidationException(Strings.NoName);
        }

        AccountObj account;
        try {
            account = accountData.getGuestAccountByName(name);
        } catch (AccountNotFoundException e) {
            account = accountData.createGuestAccount(name);
        }

        accountData.setStayLoggedIn(account.getId(), stayLoggedIn);
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
            preferencesObj lp = accountData.getLocalPreferences();
            if (lp.autoLogin() && lp.accountId()>0) {
                return accountData.getGuestAccountById(lp.accountId());
            }
        } catch (DataInaccessibleException | AccountNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setActiveCourse(AccountObj account, int courseId) throws InputValidationException {
        if (courseId<0) {
            throw new InputValidationException(Strings.CourseNotFound(courseId));
        }
        accountData.setActiveCourse(account.getId(), courseId);
        account.setActiveCourseId(courseId);
    }
}
