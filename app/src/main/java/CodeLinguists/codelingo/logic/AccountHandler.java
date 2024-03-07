package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.persistence.persistenceExceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistenceExceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.logicExceptions.InputValidationException;
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
