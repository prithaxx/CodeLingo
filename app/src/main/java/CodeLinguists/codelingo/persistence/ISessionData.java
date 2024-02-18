package CodeLinguists.codelingo.persistence;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;

/**
 * For app state data that should persist across session
 * Such as "stay logged in" users,
 */
public interface ISessionData {
    void setActiveAccount(AccountObj account);
    CourseObj getActiveCourse();
}
