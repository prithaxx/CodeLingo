package CodeLinguists.codelingo.persistence.stubs;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ISessionData;

public class SessionDataStub implements ISessionData {

    private AccountObj activeAccount;

    public SessionDataStub() {
        this.activeAccount = null;
    }

    @Override
    public void setActiveAccount(AccountObj account) {
        this.activeAccount = account;
    }

    @Override
    public CourseObj getActiveCourse() {
        return new CourseObj(1,"Example Course", "Welcome to the example course! This is a placeholder for future courses", true, true);
    }
}