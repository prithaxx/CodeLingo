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

    @Override
    public List<AccountObj> getGuestAccounts() {
        return accountData.getGuestAccounts();
    }

    @Override
    public void createGuestAccount(String name) {
        accountData.createGuestAccount(name);
    }


    //should there be a getAccountByID in IAccountData?
    // Is there a list of accounts in the data?
    @Override
    public AccountObj getAccountById(int id) {
        if(accountData.getId() == id){

        }
        return null;
    }

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
    public void login(AccountObj account) {
        updateSessionData(account);
    }


    //should there be username and password variable in AccountObj.java?
    @Override
    public void login(String username, String password) throws AccountNotFoundException {
        AccountObj account;
        if (password == null) {

        } else {

            account = null; 
        }
        updateSessionData(account);
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
