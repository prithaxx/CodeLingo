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
    public CourseObj getCourseById(int courseId) {
        CourseObj course = null;
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM COURSE WHERE id = ?")) {
            ps.setInt(1, courseId);
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
    public List<CourseObj> getStartedCourseList() {
        List<CourseObj> startedCourses = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM COURSE WHERE isStarted = TRUE")) {
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
