package codelinguists.codelingo.unit_tests.logic.test_doubles;

import CodeLinguists.codelingo.dso.AccountObj;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.logic_exceptions.AccountPermissionException;
import CodeLinguists.codelingo.logic.logic_exceptions.InputValidationException;
import CodeLinguists.codelingo.persistence.persistence_exceptions.DataInaccessibleException;

public class AccountHandlerTestDouble implements IAccountHandler {
    public AccountObj guestLoginResponse;
    public AccountObj autoLoginResponse;
    public int exceptionThrowIndex;
    public int setActiveCourseOutput;

    public AccountHandlerTestDouble() {
        guestLoginResponse = new AccountObj(1, "name", true, 1, "username", "pw");
        autoLoginResponse = guestLoginResponse;
        exceptionThrowIndex = -1;
        setActiveCourseOutput=-1;
    }

    @Override
    public AccountObj guestLogin(String name) throws DataInaccessibleException, InputValidationException {
        if (exceptionThrowIndex == 0) {
            throw new DataInaccessibleException("exception");
        } else if (exceptionThrowIndex == 1) {
            throw new InputValidationException("exception");
        }

        return guestLoginResponse;
    }

    @Override
    public AccountObj guestLogin(String name, boolean stayLoggedIn) throws DataInaccessibleException, InputValidationException {
        if (exceptionThrowIndex == 0) {
            throw new DataInaccessibleException("exception");
        } else if (exceptionThrowIndex == 1) {
            throw new InputValidationException("exception");
        }

        return guestLoginResponse;
    }

    @Override
    public void setActiveCourse(AccountObj account, int courseId) throws InputValidationException, AccountPermissionException {
        if (exceptionThrowIndex == 0) {
            throw new InputValidationException("exception");
        } else if (exceptionThrowIndex == 1) {
            throw new AccountPermissionException("exception");
        }

        setActiveCourseOutput = courseId;
    }

    @Override
    public AccountObj autoLogin() {
        return autoLoginResponse;
    }

    @Override
    public void logout() {
    }
}
