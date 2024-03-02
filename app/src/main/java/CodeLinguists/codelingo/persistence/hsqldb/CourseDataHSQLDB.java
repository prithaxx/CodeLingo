package CodeLinguists.codelingo.persistence.hsqldb;

import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.ICourseData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDataHSQLDB implements ICourseData {
    private final String dbPath;

    public CourseDataHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public CourseObj getCourseById(int courseId, int accountId) {
        CourseObj course = null;
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.description, cc.isStarted, cc.isCompleted " +
                                                                    "FROM COURSE c " +
                                                                    "JOIN COURSE_COMPLETION cc ON c.id = cc.courseId " +
                                                                    "WHERE c.id = ? AND cc.accountId = ?")) {
            ps.setInt(1, courseId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                course = new CourseObj( rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getString("description"),
                                        rs.getBoolean("isStarted"),
                                        rs.getBoolean("isCompleted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<CourseObj> getStartedCourseList(int accountId) {
        List<CourseObj> startedCourses = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT c.id, c.name, c.description, cc.isStarted, cc.isCompleted " +
                                                                    "FROM COURSE c " +
                                                                    "JOIN COURSE_COMPLETION cc ON c.id = cc.courseId " +
                                                                    "WHERE cc.isStarted = TRUE AND cc.accountId = ?")) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            ps.close();

            while (rs.next()) {
                startedCourses.add(new CourseObj(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("isStarted"),
                        rs.getBoolean("isCompleted")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return startedCourses;
    }
}
