package codelinguists.codelingo.integration_tests;

import org.junit.Test;

import java.io.IOException;

import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.exceptions.AccountPermissionException;
import CodeLinguists.codelingo.exceptions.CourseNotFoundException;
import CodeLinguists.codelingo.exceptions.DataInaccessibleException;
import CodeLinguists.codelingo.logic.ISessionManager;
import codelinguists.codelingo.utils.SqlDbIT;

public class SessionManagerLogoutIT extends SqlDbIT {
    ISessionManager sessionManager;

    @Override
    public void setup() throws IOException {
        super.setup();
        sessionManager = Services.getSessionManager();
    }
}
