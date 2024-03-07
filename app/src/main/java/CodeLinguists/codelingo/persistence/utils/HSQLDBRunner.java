package CodeLinguists.codelingo.persistence.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HSQLDBRunner implements ISqlRunner {
    private final String dbUrl;

    public HSQLDBRunner(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(dbUrl, "SA", "");
    }

    @Override
    public ResultSet selectAccountByName(String name) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (
                Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE name = ?");
                ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        }
    }

    @Override
    public ResultSet insertGuestAccount(String name) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (
                Connection connection = connect();
                PreparedStatement psInsert = connection.prepareStatement("INSERT INTO PUBLIC.ACCOUNT VALUES (DEFAULT, ?, TRUE, 1, ?, '')");
                PreparedStatement psSelect = connection.prepareStatement("select * from ACCOUNT where USERNAME = ? and NAME = ?");
                ) {
            psInsert.setString(1, name);
            psInsert.setString(2, name);
            psInsert.executeUpdate();
            psInsert.close();

            //Duplicate select to avoid opening extra connections, which is very slow
            psSelect.setString(1, name);
            psSelect.setString(2, name);
            ResultSet rs = psSelect.executeQuery();
            psSelect.close();
            return rs;
        }
    }

    @Override
    public void updateAccountActiveCourse(int accountId, int courseId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (
                Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement("UPDATE ACCOUNT set ActiveCourseId = ? where id = ?");
                ) {
            ps.setInt(1, courseId);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }

    @Override
    public ResultSet selectCourseById(int courseId, int accountId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.description, cc.isStarted, cc.isCompleted " +
                                                                     "FROM COURSE c " +
                                                                     "LEFT JOIN COURSE_COMPLETION cc ON c.id = cc.courseId and cc.accountId = ?" +
                                                                     "WHERE c.id = ?")) {
            ps.setInt(2, courseId);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        }
    }

    @Override
    public ResultSet selectCourseList(int accountId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.description, cc.isStarted, cc.isCompleted " +
                     "FROM COURSE c " +
                     "JOIN COURSE_COMPLETION cc ON c.id = cc.courseId and cc.accountId = ?")) {
            ps.setInt(1, accountId);
            return ps.executeQuery();
        }
    }

    @Override
    public ResultSet selectChaptersByCourseId(int courseId, int accountId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.courseId, c.description, cc.isUnlocked, cc.isCompleted " +
                     "FROM CHAPTER c " +
                     "JOIN CHAPTER_COMPLETION cc ON c.id = cc.chapterId " +
                     "WHERE c.courseId = ? AND cc.accountId = ?")) {
            ps.setInt(1, courseId);
            ps.setInt(2, accountId);
            return ps.executeQuery();
        }
    }

    @Override
    public ResultSet selectChapterById(int chapterId, int courseId, int accountId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.courseId, c.description, cc.isUnlocked, cc.isCompleted " +
                     "FROM CHAPTER c " +
                     "LEFT JOIN CHAPTER_COMPLETION cc ON c.id = cc.chapterId and cc.accountId = ? " +
                     "WHERE c.courseId = ? AND c.id = ?")) {
            ps.setInt(1, accountId);
            ps.setInt(2, courseId);
            ps.setInt(3, chapterId);
            return ps.executeQuery();
        }
    }

    @Override
    public ResultSet selectQuizByChapterId(int chapterId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM QUIZ WHERE chapterId = ? ORDER BY id")) {
            ps.setInt(1, chapterId);
            return ps.executeQuery();
        }
    }

    @Override
    public ResultSet selectQuizById(int quizId, int chapterId) throws SQLException {
        //Uses try-with to close connection & prepared statement on exception
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM QUIZ WHERE chapterId = ? AND quizId = ?")) {
            ps.setInt(1, chapterId);
            ps.setInt(2, quizId);
            return ps.executeQuery();
        }
    }
}
