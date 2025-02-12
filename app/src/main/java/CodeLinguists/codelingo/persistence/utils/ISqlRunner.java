package CodeLinguists.codelingo.persistence.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISqlRunner {
    Connection connect() throws SQLException;

    //AccountData
    ResultSet selectGuestAccountByUsername(String name) throws SQLException;
    ResultSet insertGuestAccount(String name) throws SQLException;
    void updateAccountActiveCourse(int accountId, int courseId) throws SQLException;

    //CourseData
    ResultSet selectCourseById(int courseId, int accountId) throws SQLException;
    ResultSet selectCourseList(int accountId) throws SQLException;

    //ChapterData
    ResultSet selectChaptersByCourseId(int courseId, int accountId) throws SQLException;
    ResultSet selectChapterById(int chapterId, int courseId, int accountId) throws SQLException;

    //QuizData
    ResultSet selectQuizByChapterId(int chapterId) throws SQLException;
    ResultSet selectQuizById(int quizId, int chapterId) throws SQLException;


    //Preferences
    void updateLocalPreferencesAutoLogin(boolean stayLoggedIn, int accountId) throws SQLException;
    ResultSet selectLocalPreferences() throws SQLException;

    void insertLocalPreferences() throws SQLException;

    ResultSet selectGuestAccountById(int accountId) throws SQLException;

    ResultSet selectChapterCompletionById(int accountId, int chapterId) throws SQLException;

    void updateChapterCompletionSetComplete(int accountId, int chapterId) throws SQLException;

    void insertUnlockedIntoChapterCompletion(int accountId, int chapterId) throws SQLException;

    void updateChapterCompletion(int accountId, int chapterId, boolean setUnlocked) throws SQLException;

    void insertChapterCompletion(int accountId, int chapterId, boolean setUnlocked) throws SQLException;

    ResultSet selectChaptersInCourseAfterId(int courseId, int chapterId) throws SQLException;

    ResultSet selectFirstChaptersOfAllCourses() throws SQLException;
}
