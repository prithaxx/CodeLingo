package codelinguists.codelingo.utils;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

import CodeLinguists.codelingo.application.Services;

public abstract class SqlDbIT {
    private File tempDB;

    @Before
    public void setup() throws IOException {
        this.tempDB = TestUtils.copyDB();
    }

    @After
    public void reset() {
        this.tempDB.delete();
        Services.resetObjects();
    }
}
