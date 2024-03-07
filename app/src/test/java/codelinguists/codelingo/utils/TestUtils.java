package codelinguists.codelingo.utils;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;

import CodeLinguists.codelingo.application.Main;
import CodeLinguists.codelingo.logic.CourseHandler;
import CodeLinguists.codelingo.logic.IAccountHandler;
import CodeLinguists.codelingo.logic.ICourseHandler;
import CodeLinguists.codelingo.logic.IQuizHandler;
import CodeLinguists.codelingo.logic.SessionManager;
import CodeLinguists.codelingo.persistence.IChapterData;
import CodeLinguists.codelingo.persistence.ICourseData;


public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/CodeLingoDB.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
