package CodeLinguists.codelingo.persistence;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.persistence.persistenceExceptions.AccountNotFoundException;
import CodeLinguists.codelingo.persistence.persistenceExceptions.DataInaccessibleException;

public interface IAccountData {

    /**
     * Returns an account marked as "guest" if one matching "name" exists, else throw an exception
     *
     * @param name - search accounts by string
     * @return matching account
     */
    AccountObj getGuestAccountByName(String name) throws AccountNotFoundException;

    /**
     * creates and returns a new guest account if name is unique, else throw an exception
     *
     * @param name - name of new guest account
     * @return the new guest account
     */
    AccountObj createGuestAccount(String name) throws DataInaccessibleException;
    void setActiveCourse(int accountId, int courseId);
}
