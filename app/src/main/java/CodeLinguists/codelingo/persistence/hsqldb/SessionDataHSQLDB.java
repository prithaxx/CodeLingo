package CodeLinguists.codelingo.persistence.hsqldb;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ISessionData;

public class SessionDataHSQLDB implements ISessionData {
    private AccountObj activeAccount;
    private CourseObj activeCourse;

    @Override
    public void setActiveAccount(AccountObj account) {
        this.activeAccount = account;
    }

    @Override
    public CourseObj getActiveCourse() {
        return this.activeCourse;
    }

}
