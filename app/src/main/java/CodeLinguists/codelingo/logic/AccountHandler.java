package CodeLinguists.codelingo.logic;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.IAccountData;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.application.Services;
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
        accountData.createGuestAccount(name);
    }
    
    //remove getaccount byID

    @Override
    public CourseObj getActiveCourse() {
        AccountObj activeAccount = sessionData.getActiveAccount();
        if(activeAccount !=null){
            return activeAccount.getActiveCourse();
        }else{
            return null;
        }
    }

    @Override
    public void setActiveCourse(CourseObj course) {
        AccountObj activeAccount = sessionData.getActiveAccount();
        if(activeAccount !=null){
            activeAccount.setActiveCourse(course);
        }
    }

    /*@Override
    public void login(AccountObj account) {
        updateSessionData(account);
    }*/  
    //Prob dont need this anymore 


    @Override
    public void login(String username, String password) throws AccountNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new AccountNotFoundException("Username cannot be null or empty.");
        }

        // Logic for guest login
        if (password == null) {
            AccountObj account = accountData.getGuestAccountByName(username); // do we get guest account using name or username here
            updateSessionData(account);
            return;
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
    public AccountObj getAccountDetails() {
        return sessionData.getActiveAccount();
    }

    @Override
    public void logout() {
        sessionData.setActiveAccount(null);
    }

    private void updateSessionData(AccountObj account) {
        if (account != null) {
            sessionData.setActiveAccount(account);
        }
    }
}
