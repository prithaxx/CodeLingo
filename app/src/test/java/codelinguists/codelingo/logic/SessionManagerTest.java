package codelinguists.codelingo.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodeLinguists.codelingo.logic.SessionManager;

public class SessionManagerTest {
    @Before
    public void setup() {
        SessionManager.clearSessionData();
    }

    @Test
    public void singletonTest() {
        assertSame(SessionManager.newInstance(), SessionManager.newInstance());
    }

    @Test
    public void singletonTest() {
        assertSame(SessionManager.newInstance(), SessionManager.newInstance());
    }

    @Test
    public void singletonTest() {
        assertSame(SessionManager.newInstance(), SessionManager.newInstance());
    }


}
