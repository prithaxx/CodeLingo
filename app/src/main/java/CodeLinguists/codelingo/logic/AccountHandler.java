package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.application.Services;

import java.sql.SQLException;
import java.util.List;

public class AccountHandler implements IAccountHandler {

    private final IAccountData accountData;
    private final ISessionData sessionData;

    public AccountHandler(IAccountData accountData, ISessionData sessionData) {
        this.accountData = accountData;
        this.sessionData = sessionData;
    }

    @Override
    public AccountObj guestLogin(String name) throws DataInaccessibleException {
        if(name == null || name.isEmpty()){
            throw new InputValidationException(Strings.NoName);
        }

        AccountObj account = null;
        try {
            account = accountData.getGuestAccountByName(name);
        } catch (AccountNotFoundException e) {
            account = accountData.createGuestAccount(name);
        }

        updateSessionData(account);
        return account;
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
