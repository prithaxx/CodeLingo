package CodeLinguists.codelingo.persistence;

import CodeLinguists.codelingo.dso.AccountObj;

public interface ISessionData {

    /**
     * @return - "remember me" account
     */
    AccountObj getActiveAccount();
    void setActiveAccount(AccountObj account);
    CourseObj getActiveCourse();
}
