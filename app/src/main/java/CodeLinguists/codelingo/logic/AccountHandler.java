package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.ChapterObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.exceptions.InputValidationException;
import CodeLinguists.codelingo.exceptions.NotSignedInException;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.application.Services;

import java.util.ArrayList;
import java.util.List;

public class AccountHandler implements IAccountHandler {

    private IAccountData accountData; 
    private ISessionData sessionData; 

    public AccountHandler() {
        this.accountData = Services.getAccountData();
        this.sessionData = Services.getSessionData();
    }

    public AccountHandler(IAccountData accountData, ISessionData sessionData) {
        this.accountData = accountData;
        this.sessionData = sessionData;
    }

    @Override
    public List<AccountObj> getGuestAccounts() {
        return accountData.getGuestAccounts();
    }

    @Override
    public void createGuestAccount(String name) {
        if (name == null || name.isEmpty()) {
            throw new InputValidationException("Name cannot be empty.");
        }
        accountData.createGuestAccount(name);
    }
    
    @Override
    public CourseObj getActiveCourse() {
        AccountObj activeAccount = sessionData.getActiveAccount();
        if(activeAccount == null){
            throw new NotSignedInException();
        }
        return activeAccount.getActiveCourse();
    }

    @Override
    public void setActiveCourse(CourseObj course) {
        AccountObj activeAccount = sessionData.getActiveAccount();
        if(activeAccount == null){
            throw new NotSignedInException();
        }
        accountData.setActiveCourse(activeAccount, course);
    }

    @Override
    public void login(String username, String password) throws AccountNotFoundException {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InputValidationException("Username or password cannot be empty.");
        }

        // Logic for regular user login
        List<AccountObj> allAccounts = accountData.getAllAccounts(); 
        for (AccountObj account : allAccounts) {
            if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                updateSessionData(account);
                return;
            }
        }

        // If no matching account found
        throw new AccountNotFoundException("Invalid username or password.");
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
        return account;
    }

    @Override
    public AccountObj getAccountDetails() {
        return sessionData.getActiveAccount();
    }

    @Override
    public void logout() {
        sessionData.setActiveAccount(null);
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
