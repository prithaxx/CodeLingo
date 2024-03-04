package CodeLinguists.codelingo.persistence.sql;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ISessionData;
import CodeLinguists.codelingo.persistence.utils.ISqlRunner;

public class SessionDataSQL implements ISessionData {
    private final ISqlRunner sqlRunner;
    //TODO connect to DB
    private AccountObj activeAccount;
    private CourseObj activeCourse;

    public SessionDataSQL(ISqlRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @Override
    public void setActiveAccount(AccountObj account) {
        this.activeAccount = account;
    }

    @Override
    public CourseObj getActiveCourse() {
        return this.activeCourse;
    }

}
