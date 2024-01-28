package CodeLinguists.codelingo.logic;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;

public class AccountHandler implements  IAccountHandler{

    @Override
    public List<AccountObj> getGuestAccounts() {
        return null;
    }

    @Override
    public void createGuestAccount(String name) {

    }

    @Override
    public AccountObj getAccountById(int id) {
        return null;
    }

    @Override
    public CourseObj getActiveCourse() {
        return null;
    }

    @Override
    public void login(AccountObj account) {

    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void logout() {

    }
}
