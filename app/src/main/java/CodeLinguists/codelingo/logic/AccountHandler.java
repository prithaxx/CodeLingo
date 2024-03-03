package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
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
    public AccountObj guestLogin(String name) throws AccountNotFoundException, SQLException {
        if(name == null || name.isEmpty()){
            throw new InputValidationException("Name cannot be empty.");
        }

        AccountObj account = accountData.getGuestAccountByName(name);

        if (account==null) {
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

    @Override
    public List<ChapterObj> getActiveCourseChapters() {
        return null;
    }

    private void updateSessionData(AccountObj account) {
        if (account != null) {
            sessionData.setActiveAccount(account);
        }
    }
}
