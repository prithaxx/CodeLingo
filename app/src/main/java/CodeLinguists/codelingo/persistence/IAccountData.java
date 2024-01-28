package CodeLinguists.codelingo.persistence;

import java.util.List;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.exceptions.AccountNotFoundException;

public interface IAccountData {

    /**
     * Returns an account marked as "guest" if one matching "name" exists, else throw an exception
     *
     * @param name - search accounts by string
     * @return matching account
     * @throws AccountNotFoundException
     */
    public AccountObj getGuestAccountByName(String name);

    //return all guest accounts

    /**
     * return all guest accounts on local device
     *
     * @return list of accountObj
     */
    public List<AccountObj> getGuestAccounts();

    /**
     * creates and returns a new guest account if name is unique, else throw an exception
     *
     * @param name - name of new guest account
     * @return the new guest account
     */
    public AccountObj createGuestAccount(String name);

    /**
     * @param course - course to open on next login
     */
    public void setActiveCourse(CourseObj course);

}
