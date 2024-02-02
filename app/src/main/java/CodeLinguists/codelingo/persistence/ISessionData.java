package CodeLinguists.codelingo.persistence;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;

public interface ISessionData {

    /**
     * @return - "remember me" account
     */
    AccountObj getActiveAccount();
    void setActiveAccount(AccountObj account);
    CourseObj getActiveCourse();
}
