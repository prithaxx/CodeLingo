package CodeLinguists.codelingo.persistence.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISqlRunner {
    public Connection connect() throws SQLException;

    //AccountData
    public ResultSet selectAccountByName(String name) throws SQLException;
    public ResultSet insertGuestAccount(String name) throws SQLException;
    public void updateAccountActiveCourse(int accountId, int courseId) throws SQLException;

    //CourseData
    public ResultSet selectCourseById(int courseId, int accountId) throws SQLException;
    public ResultSet selectCourseList(int accountId) throws SQLException;

    //ChapterData
    public ResultSet selectChaptersByCourseId(int courseId, int accountId) throws SQLException;
    public ResultSet selectChapterById(int chapterId, int courseId, int accountId) throws SQLException;

    //QuizData
    public ResultSet selectQuizByChapterId(int chapterId) throws SQLException;
    public ResultSet selectQuizById(int quizId, int chapterId) throws SQLException;
}
