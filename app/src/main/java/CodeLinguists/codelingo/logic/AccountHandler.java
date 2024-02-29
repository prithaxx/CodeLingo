package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.application.Services;

import java.util.List;

public class AccountHandler implements IAccountHandler {

    private final IAccountData accountData;
    private final ISessionData sessionData;

    public AccountHandler() {
        this.accountData = Services.getAccountData();
        this.sessionData = Services.getSessionData();
    }

    public AccountHandler(IAccountData accountData, ISessionData sessionData) {
        this.accountData = accountData;
        this.sessionData = sessionData;
    }

    @Override
    public AccountObj guestLogin(String name) throws AccountNotFoundException{
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
    public CourseObj getActiveCourse() {
        return sessionData.getActiveCourse();
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
