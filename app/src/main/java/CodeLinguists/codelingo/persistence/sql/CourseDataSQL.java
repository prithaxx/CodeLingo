package CodeLinguists.codelingo.persistence.sql;

import CodeLinguists.codelingo.application.Strings;
import CodeLinguists.codelingo.dso.CourseObj;
import CodeLinguists.codelingo.persistence.persistenceExceptions.CourseNotFoundException;
import CodeLinguists.codelingo.persistence.ICourseData;
import CodeLinguists.codelingo.persistence.utils.ISqlRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDataSQL implements ICourseData {
    private final ISqlRunner sqlRunner;

    public CourseDataSQL(ISqlRunner sqlRunner) {
        this.sqlRunner = sqlRunner;
    }

    @Override
    public CourseObj getCourseById(int courseId, int accountId) throws CourseNotFoundException {
        CourseObj course = null;
        try (ResultSet rs = sqlRunner.selectCourseById(courseId, accountId);) {
            if (rs.next()) {
                return new CourseObj( rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getString("description"),
                                        rs.getBoolean("isStarted"),
                                        rs.getBoolean("isCompleted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new CourseNotFoundException(Strings.CourseNotFound(courseId));
    }

    @Override
    public List<CourseObj> getCourseList(int accountId) {
        List<CourseObj> courses = new ArrayList<>();
        try (ResultSet rs = sqlRunner.selectCourseList(accountId)) {
            while (rs.next()) {
                courses.add(new CourseObj(
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
        return courses;
    }
}
